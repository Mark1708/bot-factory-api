package com.mark1708.prediction.exception;

public class PredictionException extends RuntimeException {

  public PredictionException() {
  }

  public PredictionException(String message) {
    super(message);
  }

  public PredictionException(String message, Throwable cause) {
    super(message, cause);
  }

  public PredictionException(Throwable cause) {
    super(cause);
  }

  public PredictionException(
      String message,
      Throwable cause,
      boolean enableSuppression,
      boolean writableStackTrace
  ) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
