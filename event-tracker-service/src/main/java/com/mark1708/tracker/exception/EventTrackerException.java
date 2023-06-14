package com.mark1708.tracker.exception;

public class EventTrackerException extends RuntimeException {

  public EventTrackerException() {
  }

  public EventTrackerException(String message) {
    super(message);
  }

  public EventTrackerException(String message, Throwable cause) {
    super(message, cause);
  }

  public EventTrackerException(Throwable cause) {
    super(cause);
  }

  public EventTrackerException(
      String message,
      Throwable cause,
      boolean enableSuppression,
      boolean writableStackTrace
  ) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
