package com.mark1708.botapicore.facade.impl;

import com.mark1708.botapicore.converter.TariffConverter;
import com.mark1708.botapicore.facade.TariffFacade;
import com.mark1708.botapicore.model.entity.Bot;
import com.mark1708.botapicore.model.entity.Tariff;
import com.mark1708.botapicore.model.tariff.TariffDto;
import com.mark1708.botapicore.model.tariff.TariffInfoDto;
import com.mark1708.botapicore.service.BotService;
import com.mark1708.botapicore.service.TariffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TariffFacadeImpl implements TariffFacade {

  private final TariffService tariffService;
  private final BotService botService;

  private final TariffConverter tariffConverter;

  @Override
  public TariffDto updateTariff(String apiKey, Long id, TariffInfoDto tariffInfoDto) {
    Bot bot = botService.getBotByApiKey(apiKey);
    Tariff tariff = tariffService.getTariffById(id);
    tariff.setTimeUnit(tariffInfoDto.getTimeUnit().getValue());
    tariff.setValue(tariffInfoDto.getValue());
    tariff.setPrice(tariffInfoDto.getPrice());
    return tariffConverter.toDto(
        tariffService.saveTariff(tariff)
    );
  }

  @Override
  public boolean deleteTariff(String apiKey, Long id) {
    return tariffService.deleteTariffById(id);
  }
}
