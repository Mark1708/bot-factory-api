package com.mark1708.botapicore.controller.bot;


import com.mark1708.botapicore.facade.ServiceFacade;
import com.mark1708.botapicore.facade.UserFacade;
import com.mark1708.botapicore.model.pay.PayDto;
import com.mark1708.botapicore.model.role.RoleDto;
import com.mark1708.botapicore.model.service.CreateServiceDto;
import com.mark1708.botapicore.model.service.ServiceDto;
import com.mark1708.botapicore.model.service.ServiceInfoDto;
import com.mark1708.botapicore.model.subscription.SubscriptionDto;
import com.mark1708.botapicore.model.tariff.TariffDto;
import com.mark1708.botapicore.model.user.CreateUserDto;
import com.mark1708.botapicore.model.user.UserDto;
import com.mark1708.botapicore.model.user.UserInfoDto;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bot/services")
public class ServiceController {

  private final ServiceFacade serviceFacade;

  @GetMapping
  public List<ServiceDto> getServices(@RequestHeader("Authorization") String apiKey) {
    return serviceFacade.getServices(apiKey);
  }

  @GetMapping("/search")
  public List<ServiceDto> searchServices(
      @RequestHeader("Authorization") String apiKey,
      @RequestParam(name = "query") String query,
      @Parameter(description = "name, service-type")
      @RequestParam(name = "type", defaultValue = "service-type") String type
  ) {
    return serviceFacade.searchServices(apiKey, query, type);
  }

  @GetMapping("/{id}")
  public ServiceDto getService(
      @RequestHeader("Authorization") String apiKey,
      @PathVariable Long id
  ) {
    log.debug("Get service: [{}]", id);
    return serviceFacade.getService(apiKey, id);
  }



  @PostMapping
  public ServiceDto createService(
      @RequestHeader("Authorization") String apiKey,
      @RequestBody CreateServiceDto createServiceDto
  ) {
    return serviceFacade.createService(apiKey, createServiceDto);
  }


  @PutMapping("/{id}")
  public ServiceDto updateServiceInfo(
      @RequestHeader("Authorization") String apiKey,
      @PathVariable Long id,
      @RequestBody ServiceInfoDto serviceInfoDto
  ) {
    log.debug("Update service info: [{}, {}]", id, serviceFacade);
    return serviceFacade.updateServiceInfo(apiKey, id, serviceInfoDto);
  }

  @DeleteMapping("/{id}")
  public boolean deleteService(
      @RequestHeader("Authorization") String apiKey,
      @PathVariable Long id
      ) {
    log.debug("Delete service: [{}]", id);
    return serviceFacade.deleteService(apiKey, id);
  }

  @PostMapping("/{id}/tariff")
  public ServiceDto addServiceTariff(
      @RequestHeader("Authorization") String apiKey,
      @PathVariable Long id,
      @RequestBody TariffDto tariffDto
  ) {
    log.debug("Add tariff to service: [{}, {}]", id, tariffDto);
    return serviceFacade.addServiceTariff(apiKey, id, tariffDto);
  }

  @DeleteMapping("/{id}/tariff")
  public ServiceDto deleteServiceTariff(
      @RequestHeader("Authorization") String apiKey,
      @PathVariable Long id,
      @RequestBody TariffDto tariffDto
  ) {
    log.debug("Delete tariff from service: [{}, {}]", id, tariffDto);
    return serviceFacade.deleteServiceTariff(apiKey, id, tariffDto);
  }

  @GetMapping("/{id}/subscriptions")
  public List<SubscriptionDto> getServiceSubscriptions(
      @RequestHeader("Authorization") String apiKey,
      @PathVariable Long id
  ) {
    log.debug("Get service's subscriptions: {}", id);
    return serviceFacade.getServiceSubscriptions(apiKey, id);
  }

  @GetMapping("/{id}/pays")
  public List<PayDto> getServicePays(
      @RequestHeader("Authorization") String apiKey,
      @PathVariable Long id
  ) {
    log.debug("Get service's pays: {}", id);
    return serviceFacade.getServicePays(apiKey, id);
  }
}
