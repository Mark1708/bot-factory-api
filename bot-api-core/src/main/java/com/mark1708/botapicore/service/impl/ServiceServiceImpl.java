package com.mark1708.botapicore.service.impl;

import com.mark1708.botapicore.repository.ServiceRepository;
import com.mark1708.botapicore.service.ServiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {

  private final ServiceRepository repository;

}
