package com.mark1708.prediction.util;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import org.apache.spark.sql.api.java.UDF1;

public class UdfMethods {

  public static UDF1<Timestamp, Integer> dayInMonth() {
    return (s1) -> s1.toLocalDateTime().getMonth().maxLength();
  }

  public static UDF1<Timestamp, Integer> isWeekend() {
    return (s1) -> {
      DayOfWeek dayOfWeek = s1.toLocalDateTime().getDayOfWeek();
      return (dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY)) ? 1 : 0;
    };
  }
}
