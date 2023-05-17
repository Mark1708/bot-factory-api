package com.mark1708.statistics.exception.http;


import com.mark1708.statistics.exception.StatisticsException;

public class BadRequestException extends StatisticsException {

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
