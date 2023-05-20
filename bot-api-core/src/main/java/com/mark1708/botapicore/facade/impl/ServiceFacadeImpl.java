package com.mark1708.botapicore.facade.impl;

import com.mark1708.botapicore.converter.PayConverter;
import com.mark1708.botapicore.converter.ServiceConverter;
import com.mark1708.botapicore.converter.SubscriptionConverter;
import com.mark1708.botapicore.exception.http.BadRequestException;
import com.mark1708.botapicore.facade.ServiceFacade;
import com.mark1708.botapicore.model.entity.Bot;
import com.mark1708.botapicore.model.entity.SubService;
import com.mark1708.botapicore.model.entity.Tariff;
import com.mark1708.botapicore.model.pay.PayDto;
import com.mark1708.botapicore.model.service.CreateServiceDto;
import com.mark1708.botapicore.model.service.ServiceDto;
import com.mark1708.botapicore.model.service.ServiceInfoDto;
import com.mark1708.botapicore.model.subscription.SubscriptionDto;
import com.mark1708.botapicore.model.tariff.TariffDto;
import com.mark1708.botapicore.service.BotService;
import com.mark1708.botapicore.service.PayService;
import com.mark1708.botapicore.service.ServiceService;
import com.mark1708.botapicore.service.SubscriptionService;
import com.mark1708.botapicore.service.TariffService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServiceFacadeImpl implements ServiceFacade {

  private final ServiceService serviceService;
  private final BotService botService;
  private final TariffService tariffService;
  private final PayService payService;
  private final SubscriptionService subscriptionService;

  private final ServiceConverter serviceConverter;
  private final PayConverter payConverter;
  private final SubscriptionConverter subscriptionConverter;

  @Override
  public List<ServiceDto> getServices(String apiKey) {
    Bot bot = botService.getBotByApiKey(apiKey);

    return serviceConverter.toDto(
        serviceService.getServicesByBotId(bot.getId())
    );
  }

  @Override
  public List<ServiceDto> searchServices(String apiKey, String query, String type) {
    Bot bot = botService.getBotByApiKey(apiKey);
    switch (type) {
      case "name":
        return serviceConverter.toDto(
            serviceService.searchServicesByBotIdAndName(bot.getId(), query)
        );
      case "service-type":
        switch (query) {
          case "date":
            return serviceConverter.toDto(
                serviceService.searchServicesByBotIdAndType(bot.getId(), 1)
            );
          case "counter":
            return serviceConverter.toDto(
                serviceService.searchServicesByBotIdAndType(bot.getId(), 2)
            );
          default:
            throw new BadRequestException(
                "Unrecognized service-type - " + type + ".Must be date/counter.");

        }
      default:
        throw new BadRequestException("Unrecognized type - " + type);
    }
  }

  @Override
  public ServiceDto getService(String apiKey, Long id) {
    Bot bot = botService.getBotByApiKey(apiKey);

    return serviceConverter.toDto(
        serviceService.getServiceByIdAndBotId(id, bot.getId())
    );
  }

  @Override
  public ServiceDto createService(String apiKey, CreateServiceDto createServiceDto) {
    Bot bot = botService.getBotByApiKey(apiKey);
    String name = createServiceDto.getName();
    serviceService.findServiceByNameAndBotId(name, bot.getId())
        .ifPresent(s -> {
              throw new BadRequestException(
                  String.format("Service with name - %s, already exist!", name)
              );
            }
        );
    int type;
    switch (createServiceDto.getType()) {
      case "date":
        type = 1;
        break;
      case "counter":
        type = 2;
        break;
      default:
        throw new BadRequestException(
            "Unrecognized type - " + createServiceDto.getType() + ".Must be date/counter.");
    }
    SubService service = serviceService.saveService(
        SubService.builder()
            .bot(bot)
            .type(type)
            .name(name)
            .build()
    );

    List<Tariff> tariffs = createServiceDto.getTariffs().stream().map(dto -> {
      Tariff tariff;
      if (type == 1) {
        tariff = Tariff.builder()
            .service(service)
            .timeUnit(dto.getTimeUnit().getValue())
            .value(dto.getValue())
            .price(dto.getPrice())
            .build();
      } else {
        tariff = Tariff.builder()
            .service(service)
            .value(dto.getValue())
            .price(dto.getPrice())
            .build();
      }
      return tariffService.saveTariff(tariff);
    }).collect(Collectors.toList());
    service.setTariffs(tariffs);

    return serviceConverter.toDto(service);
  }

  @Override
  public ServiceDto updateServiceInfo(String apiKey, Long id, ServiceInfoDto serviceInfoDto) {
    Bot bot = botService.getBotByApiKey(apiKey);
    String name = serviceInfoDto.getName();
    serviceService.findServiceByNameAndBotId(name, bot.getId())
        .ifPresent(s -> {
              throw new BadRequestException(
                  String.format("Service with name - %s, already exist!", name)
              );
            }
        );

    SubService service = serviceService.getServiceByIdAndBotId(id, bot.getId());
    service.setName(name);

    return serviceConverter.toDto(
        serviceService.saveService(service)
    );
  }

  @Override
  public boolean deleteService(String apiKey, Long id) {
    Bot bot = botService.getBotByApiKey(apiKey);
    return serviceService.deleteServiceByIdAndBotId(id, bot.getId());
  }

  @Override
  public ServiceDto addServiceTariff(String apiKey, Long id, TariffDto tariffDto) {
    Bot bot = botService.getBotByApiKey(apiKey);
    SubService service = serviceService.getServiceByIdAndBotId(id, bot.getId());
    Tariff tariff;
    if (service.getType() == 1) {
      tariff = Tariff.builder()
          .service(service)
          .timeUnit(tariffDto.getTimeUnit().getValue())
          .value(tariffDto.getValue())
          .price(tariffDto.getPrice())
          .build();
    } else {
      tariff = Tariff.builder()
          .service(service)
          .value(tariffDto.getValue())
          .price(tariffDto.getPrice())
          .build();
    }
    service.getTariffs().add(tariffService.saveTariff(tariff));
    return serviceConverter.toDto(service);
  }

  @Override
  public ServiceDto deleteServiceTariff(String apiKey, Long id, TariffDto tariffDto) {
    Bot bot = botService.getBotByApiKey(apiKey);
    tariffService.deleteTariffByIdAndServiceId(tariffDto.getId(), id);
    SubService service = serviceService.getServiceByIdAndBotId(id, bot.getId());
    return serviceConverter.toDto(service);
  }

  @Override
  public List<SubscriptionDto> getServiceSubscriptions(String apiKey, Long id) {
    Bot bot = botService.getBotByApiKey(apiKey);
    return subscriptionConverter.toDto(
        subscriptionService.getSubscriptionsByBotIdAndServiceId(bot.getId(), id)
    );
  }

  @Override
  public List<PayDto> getServicePays(String apiKey, Long id) {
    Bot bot = botService.getBotByApiKey(apiKey);
    return payConverter.toDto(
        payService.getPaysByBotIdAndServiceId(bot.getId(), id)
    );
  }
}
