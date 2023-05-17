package com.mark1708.statistics.exception;

public class StatisticsException extends RuntimeException {

  public StatisticsException() {
  }

  public StatisticsException(String message) {
    super(message);
  }

  public StatisticsException(String message, Throwable cause) {
    super(message, cause);
  }

  public StatisticsException(Throwable cause) {
    super(cause);
  }

  public StatisticsException(
      String message,
      Throwable cause,
      boolean enableSuppression,
      boolean writableStackTrace
  ) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
