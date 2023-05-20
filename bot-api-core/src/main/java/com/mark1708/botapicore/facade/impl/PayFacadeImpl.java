package com.mark1708.botapicore.facade.impl;

import com.mark1708.botapicore.converter.PayConverter;
import com.mark1708.botapicore.facade.PayFacade;
import com.mark1708.botapicore.model.entity.SubService;
import com.mark1708.botapicore.model.entity.Pay;
import com.mark1708.botapicore.model.entity.Tariff;
import com.mark1708.botapicore.model.entity.User;
import com.mark1708.botapicore.model.pay.CreatePayDto;
import com.mark1708.botapicore.model.pay.PayDto;
import com.mark1708.botapicore.service.PayService;
import com.mark1708.botapicore.service.TariffService;
import com.mark1708.botapicore.service.UserService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PayFacadeImpl implements PayFacade {

  private final PayService payService;
  private final UserService userService;
  private final TariffService tariffService;

  private final PayConverter payConverter;

  @Override
  public PayDto createPay(String apiKey, CreatePayDto createPayDto) {
    User user = userService.getUserById(createPayDto.getUserId());
    Tariff tariff = tariffService.getTariffById(createPayDto.getTariffId());
    SubService service = tariff.getService();

    Pay pay = Pay.builder()
        .service(service)
        .user(user)
        .tariff(tariff)
        .payDate(LocalDateTime.now())
        .payload(createPayDto.getPayload())
        .amount(createPayDto.getAmount())
        .build();

    return payConverter.toDto(
      payService.savePay(pay)
    );
  }

  @Override
  public boolean deletePay(String apiKey, Long id) {
    return payService.deletePayById(id);
  }
}
