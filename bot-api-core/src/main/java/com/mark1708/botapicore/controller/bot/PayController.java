package com.mark1708.botapicore.controller.bot;


import com.mark1708.botapicore.facade.PayFacade;
import com.mark1708.botapicore.model.pay.CreatePayDto;
import com.mark1708.botapicore.model.pay.PayDto;
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
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bot/pays")
public class PayController {

  private final PayFacade payFacade;


  @PostMapping
  public PayDto createPay(
      @RequestHeader("Authorization") String apiKey,
      @RequestBody CreatePayDto createPayDto
  ) {
    log.debug("Create pay: [{}]", createPayDto);
    return payFacade.createPay(apiKey, createPayDto);
  }

  @DeleteMapping("/{id}")
  public boolean deletePay(
      @RequestHeader("Authorization") String apiKey,
      @PathVariable Long id
  ) {
    log.debug("Delete pay: [{}]", id);
    return payFacade.deletePay(apiKey, id);
  }
}
