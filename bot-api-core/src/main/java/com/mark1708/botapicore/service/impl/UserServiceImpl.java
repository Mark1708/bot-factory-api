package com.mark1708.botapicore.service.impl;

import com.mark1708.botapicore.repository.UserRepository;
import com.mark1708.botapicore.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository repository;

}
