package com.mark1708.botapicore.service;

import com.mark1708.botapicore.model.entity.Pay;
import java.util.List;

public interface PayService {

  List<Pay> getPaysByBotId(Long botId);

  Pay savePay(Pay pay);

  boolean deletePayById(Long id);

  List<Pay> getPaysByBotIdAndServiceId(Long botId, Long serviceId);

  List<Pay> getPaysByBotIdAndUserId(Long botId, Long userId);
}
