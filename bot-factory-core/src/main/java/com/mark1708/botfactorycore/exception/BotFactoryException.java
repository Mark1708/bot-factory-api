package com.mark1708.botfactorycore.exception;

public class BotFactoryException extends RuntimeException {

  public BotFactoryException() {
  }

  public BotFactoryException(String message) {
    super(message);
  }

  public BotFactoryException(String message, Throwable cause) {
    super(message, cause);
  }

  public BotFactoryException(Throwable cause) {
    super(cause);
  }

  public BotFactoryException(
      String message,
      Throwable cause,
      boolean enableSuppression,
      boolean writableStackTrace
  ) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
