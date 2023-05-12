package com.mark1708.botapicore.service.impl;

import com.mark1708.botapicore.repository.PayRepository;
import com.mark1708.botapicore.service.PayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PayServiceImpl implements PayService {

  private final PayRepository repository;

}
