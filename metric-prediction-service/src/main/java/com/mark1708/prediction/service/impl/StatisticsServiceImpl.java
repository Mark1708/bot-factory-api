package com.mark1708.prediction.service.impl;

import com.mark1708.prediction.repository.StatisticsRepository;
import com.mark1708.prediction.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

  private final StatisticsRepository statisticsRepository;

  @Override
  public Dataset<Row> getUsersByCol(Long botId, String columnName) {
    return statisticsRepository
        .getDatasetByBotId(
            "users", "date_time",
            columnName, botId
        );
  }

  @Override
  public Dataset<Row> getActiveUsers(Long botId) {
    return statisticsRepository
        .getDatasetByBotId(
            "users", "date_time",
            "count_all - count_blocked", botId
        );
  }

  @Override
  public Dataset<Row> getPaysByCol(Long botId, Long serviceId, String value) {
    return statisticsRepository
        .getDatasetByBotIdAndServiceId(
            "pays", "date_time",
            value, botId, serviceId, true
        );
  }

  @Override
  public Dataset<Row> getPaysByCol(Long botId, Long serviceId, Long tariffId, String value) {
    return statisticsRepository
        .getDatasetByBotIdAndServiceIdAndTariffId(
            "pays", "date_time",
            value, botId, serviceId, tariffId
        );
  }

  @Override
  public Dataset<Row> getServiceSubsByCol(Long botId, Long serviceId, String value) {
    return statisticsRepository
        .getDatasetByBotIdAndServiceId(
            "pays", "date_time",
            value, botId, serviceId, false
        );
  }

}
