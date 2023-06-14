package com.mark1708.tracker.exception.http;


import com.mark1708.tracker.exception.EventTrackerException;

public class BadRequestException extends EventTrackerException {

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
