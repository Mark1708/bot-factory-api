package com.mark1708.botapicore.facade;

import com.mark1708.botapicore.model.pay.CreatePayDto;
import com.mark1708.botapicore.model.pay.PayDto;

public interface PayFacade {

  PayDto createPay(String apiKey, CreatePayDto createPayDto);

  boolean deletePay(String apiKey, Long id);
}
