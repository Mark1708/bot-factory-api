package com.mark1708.prediction.service;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

public interface StatisticsService {

  Dataset<Row> getUsersByCol(Long botId, String columnName);

  Dataset<Row> getActiveUsers(Long botId);

  Dataset<Row> getPaysByCol(Long botId, Long serviceId, String value);

  Dataset<Row> getPaysByCol(Long botId, Long serviceId, Long tariffId, String value);

  Dataset<Row> getServiceSubsByCol(Long botId, Long serviceId, String value);
}
