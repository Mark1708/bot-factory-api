package com.mark1708.botapicore.facade;

import com.mark1708.botapicore.model.pay.PayDto;
import com.mark1708.botapicore.model.service.CreateServiceDto;
import com.mark1708.botapicore.model.service.ServiceDto;
import com.mark1708.botapicore.model.service.ServiceInfoDto;
import com.mark1708.botapicore.model.subscription.SubscriptionDto;
import com.mark1708.botapicore.model.tariff.TariffDto;
import java.util.List;

public interface ServiceFacade {

  List<ServiceDto> getServices(String apiKey);

  List<ServiceDto> searchServices(String apiKey, String query, String type);

  ServiceDto getService(String apiKey, Long id);

  ServiceDto createService(String apiKey, CreateServiceDto createServiceDto);

  ServiceDto updateServiceInfo(String apiKey, Long id, ServiceInfoDto serviceInfoDto);

  boolean deleteService(String apiKey, Long id);

  ServiceDto addServiceTariff(String apiKey, Long id, TariffDto tariffDto);

  ServiceDto deleteServiceTariff(String apiKey, Long id, TariffDto tariffDto);

  List<SubscriptionDto> getServiceSubscriptions(String apiKey, Long id);

  List<PayDto> getServicePays(String apiKey, Long id);
}
