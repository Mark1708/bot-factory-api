package com.mark1708.prediction.repository;

import com.mark1708.prediction.configuration.StatisticsDbConfiguration;
import com.mark1708.prediction.exception.http.QueryType;
import com.mark1708.prediction.exception.http.ResourceNotFoundException;
import com.mark1708.prediction.exception.http.ResourceType;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class StatisticsRepository {

  private final String dbUrl;
  private final SparkSession sparkSession;
  private final Properties connectionProperties;

  public StatisticsRepository(StatisticsDbConfiguration dbConfiguration,
      SparkSession sparkSession) {
    this.sparkSession = sparkSession;
    this.dbUrl = dbConfiguration.getUrl();
    this.connectionProperties = new Properties();
    connectionProperties.put("driver", dbConfiguration.getDriverClassName());
    connectionProperties.put("user", dbConfiguration.getUsername());
    connectionProperties.put("password", dbConfiguration.getPassword());
  }

  public Dataset<Row> getDatasetByBotId(
      String tableName,
      String dateTimeColName,
      String targetColName,
      long botId
  ) {
    try {
      return sparkSession.read()
          .jdbc(
              dbUrl,
              String.format(
                  "(SELECT %s as date_time, %s as target FROM %s WHERE bot_id = %d) as subquery",
                  dateTimeColName, targetColName, tableName, botId
              ),
              connectionProperties
          );
    } catch (Exception e) {
      throw new ResourceNotFoundException(ResourceType.STATISTICS, QueryType.BOT_ID, botId);
    }
  }

  public Dataset<Row> getDatasetByBotIdAndServiceId(
      String tableName,
      String dateTimeColName,
      String targetColName,
      long botId,
      long serviceId,
      boolean withGroupBy
  ) {
    try {
      String select;
      if (withGroupBy) {
        select = String.format("SELECT %s as date_time, SUM(%s) as target FROM %s "
            + "WHERE bot_id = %d AND service_id = %d GROUP BY date_time",
            dateTimeColName, targetColName, tableName, botId, serviceId
        );
      } else {
        select = String.format("SELECT %s as date_time, SUM(%s) as target FROM %s "
                + "WHERE bot_id = %d AND service_id = %d",
            dateTimeColName, targetColName, tableName, botId, serviceId
        );
      }
      return sparkSession.read()
          .jdbc(
              dbUrl,
              String.format("(%s) as subquery", select),
              connectionProperties
          );
    } catch (Exception e) {
      throw new ResourceNotFoundException(ResourceType.STATISTICS, QueryType.BOT_ID, botId);
    }
  }

  public Dataset<Row> getDatasetByBotIdAndServiceIdAndTariffId(
      String tableName,
      String dateTimeColName,
      String targetColName,
      long botId,
      long serviceId,
      long tariffId
  ) {
    try {
      return sparkSession.read()
          .jdbc(
              dbUrl,
              String.format(
                  "(SELECT %s as date_time, %s as target FROM %s "
                      + "WHERE bot_id = %d AND service_id = %d AND tariff_id = %d) as subquery",
                  dateTimeColName, targetColName, tableName, botId, serviceId, tariffId
              ),
              connectionProperties
          );
    } catch (Exception e) {
      throw new ResourceNotFoundException(ResourceType.STATISTICS, QueryType.BOT_ID, botId);
    }
  }
}
