package com.mark1708.statistics.service.impl;

import com.mark1708.statistics.repository.PaysStatisticsRepository;
import com.mark1708.statistics.service.PaysStatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaysStatisticsServiceImpl implements PaysStatisticsService {

  private final PaysStatisticsRepository repository;
}
