package com.mark1708.botapicore.exception.http;


import com.mark1708.botapicore.exception.BotApiException;

public class BadRequestException extends BotApiException {

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
