package com.mark1708.botapicore.service.impl;

import com.mark1708.botapicore.repository.TariffRepository;
import com.mark1708.botapicore.service.TariffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TariffServiceImpl implements TariffService {

  private final TariffRepository repository;

}
