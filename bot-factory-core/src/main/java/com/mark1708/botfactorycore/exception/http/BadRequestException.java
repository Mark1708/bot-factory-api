package com.mark1708.botfactorycore.exception.http;


import com.mark1708.botfactorycore.exception.BotFactoryException;

public class BadRequestException extends BotFactoryException {

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
