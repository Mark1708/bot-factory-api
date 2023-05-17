package com.mark1708.statistics.service.impl;

import com.mark1708.statistics.repository.SubscriptionsStatisticsRepository;
import com.mark1708.statistics.service.SubscriptionsStatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionsStatisticsServiceImpl implements SubscriptionsStatisticsService {

  private final SubscriptionsStatisticsRepository repository;

}
