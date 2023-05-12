package com.mark1708.botapicore.exception;

public class BotApiException extends RuntimeException {

  public BotApiException() {
  }

  public BotApiException(String message) {
    super(message);
  }

  public BotApiException(String message, Throwable cause) {
    super(message, cause);
  }

  public BotApiException(Throwable cause) {
    super(cause);
  }

  public BotApiException(
      String message,
      Throwable cause,
      boolean enableSuppression,
      boolean writableStackTrace
  ) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
