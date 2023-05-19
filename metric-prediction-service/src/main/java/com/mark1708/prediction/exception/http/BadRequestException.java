package com.mark1708.prediction.exception.http;


import com.mark1708.prediction.exception.PredictionException;

public class BadRequestException extends PredictionException {

  public BadRequestException(String message) {
    super(message);
  }

  public BadRequestException(Throwable cause) {
    super(cause);
  }

  public BadRequestException(String message, Throwable cause) {
    super(message, cause);
  }
}
