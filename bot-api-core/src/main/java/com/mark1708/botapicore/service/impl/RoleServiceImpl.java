package com.mark1708.botapicore.service.impl;

import com.mark1708.botapicore.repository.RoleRepository;
import com.mark1708.botapicore.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

  private final RoleRepository repository;

}
