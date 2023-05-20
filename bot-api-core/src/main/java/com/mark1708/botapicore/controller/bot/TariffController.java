package com.mark1708.botapicore.controller.bot;


import com.mark1708.botapicore.facade.TariffFacade;
import com.mark1708.botapicore.model.role.RoleDto;
import com.mark1708.botapicore.model.tariff.TariffDto;
import com.mark1708.botapicore.model.tariff.TariffInfoDto;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bot/tariffs")
public class TariffController {

  private final TariffFacade tariffFacade;

  @PutMapping("/{id}")
  public TariffDto updateTariff(
      @RequestHeader("Authorization") String apiKey,
      @PathVariable Long id,
      @RequestBody TariffInfoDto tariffInfoDto
  ) {
    log.debug("Update role info: [{}, {}]", id, tariffInfoDto);
    return tariffFacade.updateTariff(apiKey, id, tariffInfoDto);
  }

  @DeleteMapping("/{id}")
  public boolean deleteTariff(
      @RequestHeader("Authorization") String apiKey,
      @PathVariable Long id
  ) {
    log.debug("Delete pay: [{}]", id);
    return tariffFacade.deleteTariff(apiKey, id);
  }
}
