package com.mark1708.botapicore.service;

import com.mark1708.botapicore.model.entity.Tariff;

public interface TariffService {

  Tariff getTariffById(Long id);

  Tariff saveTariff(Tariff tariff);

  void deleteTariffByIdAndServiceId(Long id, Long serviceId);

  boolean deleteTariffById(Long id);
}
