package com.mark1708.prediction.util;

import static org.apache.spark.sql.functions.col;

import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.storage.StorageLevel;

public class TimeSeriesSplit {

  private final String dateTimeName;
  private Dataset<Row> inputData;
  private Column isFutureCol;

  public TimeSeriesSplit(Dataset<Row> inputData) {
    this.inputData = inputData;
    this.isFutureCol = col("is_future");
    String[] columns = inputData.columns();
    this.dateTimeName = columns[0];
  }

  public Dataset<Row> getTrainDataset() {
    return getDataByFutureCol(false);
  }

  public Dataset<Row> getEvalDataset() {
    return getDataByFutureCol(true);
  }

  private Dataset<Row> getDataByFutureCol(boolean other) {
    return this.inputData
        .filter(col("is_future").equalTo(other))
        .orderBy(col(dateTimeName)).persist(StorageLevel.MEMORY_AND_DISK());
  }
}
