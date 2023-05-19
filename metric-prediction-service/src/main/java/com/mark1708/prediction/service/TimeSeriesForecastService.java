package com.mark1708.prediction.service;

import com.mark1708.clients.prediction.dto.PredictedItem;
import java.util.List;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

public interface TimeSeriesForecastService {

  List<PredictedItem> predict(Dataset<Row> originalData, String orderId, Integer days);
}
