package com.mark1708.botapicore.facade;

import com.mark1708.botapicore.model.tariff.TariffDto;
import com.mark1708.botapicore.model.tariff.TariffInfoDto;

public interface TariffFacade {

  TariffDto updateTariff(String apiKey, Long id, TariffInfoDto tariffInfoDto);

  boolean deleteTariff(String apiKey, Long id);
}
