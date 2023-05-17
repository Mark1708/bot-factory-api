package com.mark1708.storageservice.exception.http;


import com.mark1708.storageservice.exception.StorageException;

public class BadRequestException extends StorageException {

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
