package com.mark1708.botapicore.service.impl;

import com.mark1708.botapicore.exception.http.QueryType;
import com.mark1708.botapicore.exception.http.ResourceNotFoundException;
import com.mark1708.botapicore.exception.http.ResourceType;
import com.mark1708.botapicore.model.entity.Pay;
import com.mark1708.botapicore.repository.PayRepository;
import com.mark1708.botapicore.service.PayService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PayServiceImpl implements PayService {

  private final PayRepository repository;

  @Override
  public List<Pay> getPaysByBotId(Long botId) {
    return repository.findAllByBotId(botId);
  }

  @Override
  public List<Pay> getPaysByBotIdAndServiceId(Long botId, Long serviceId) {
    return repository.findAllByBotIdAndServiceId(botId, serviceId);
  }

  @Override
  public List<Pay> getPaysByBotIdAndUserId(Long botId, Long userId) {
    return repository.findAllByBotIdAndUserId(botId, userId);
  }

  @Override
  public Pay savePay(Pay pay) {
    return repository.saveAndFlush(pay);
  }

  @Override
  public boolean deletePayById(Long id) {
    try {
      repository.deleteById(id);
      return true;
    } catch (Exception e) {
      throw new ResourceNotFoundException(ResourceType.PAY, QueryType.ID, id);
    }
  }
}
