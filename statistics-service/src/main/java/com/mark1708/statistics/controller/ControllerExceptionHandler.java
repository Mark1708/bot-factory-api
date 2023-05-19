package com.mark1708.statistics.controller;

import com.mark1708.statistics.exception.error.ApiError;
import com.mark1708.statistics.exception.error.ForbiddenError;
import com.mark1708.statistics.exception.error.NotFoundError;
import com.mark1708.statistics.exception.error.SimpleApiError;
import com.mark1708.statistics.exception.http.BadRequestException;
import com.mark1708.statistics.exception.http.ResourceForbiddenException;
import com.mark1708.statistics.exception.http.ResourceNotFoundException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

  private static final String BAD_REQUEST_LOG_MSG = "BAD_REQUEST occurred during request processing";
  private static final String NOT_FOUND_LOG_MSG = "NOT_FOUND occurred during request processing";
  private static final String FORBIDDEN_LOG_MSG = "FORBIDDEN occurred during request processing";
  private static final String INTERNAL_SERVER_ERROR_LOG_MSG =
      "INTERNAL_SERVER_ERROR occurred during request processing";
  private static final String NESTED_EXCEPTION_PREFIX = "; nested exception";

  @ResponseBody
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(value = ResourceNotFoundException.class)
  public ApiError handleNoResourceFoundException(ResourceNotFoundException e) {
    log.warn(NOT_FOUND_LOG_MSG, e);
    return new NotFoundError<>(e.getType(), e.getQueryType(), e.getValues(), e.getMessage());
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.FORBIDDEN)
  @ExceptionHandler(value = ResourceForbiddenException.class)
  public ApiError handleResourceForbiddenException(ResourceForbiddenException e) {
    log.warn(FORBIDDEN_LOG_MSG, e);
    return new ForbiddenError<>(e.getType(), e.getQueryType(), e.getValues(), e.getMessage());
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BadRequestException.class)
  public ApiError handle(BadRequestException e) {
    log.warn(BAD_REQUEST_LOG_MSG, e);
    return new SimpleApiError(e.getMessage());
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
  public ApiError handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
    log.warn(BAD_REQUEST_LOG_MSG, e);
    return new SimpleApiError(
        String.format("Invalid value=[%s] for key=[%s] specified", e.getValue(), e.getName())
    );
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = IllegalArgumentException.class)
  public ApiError handleIllegalArgumentException(IllegalArgumentException e) {
    log.warn(BAD_REQUEST_LOG_MSG, e);
    return new SimpleApiError(e.getMessage());
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Throwable.class)
  public ApiError defaultExceptionHandler(final Throwable e) {
    log.error(INTERNAL_SERVER_ERROR_LOG_MSG, e);
    return new SimpleApiError(e.getMessage());
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ApiError handle(final MethodArgumentNotValidException e) {
    log.warn(BAD_REQUEST_LOG_MSG, e);
    return new SimpleApiError(buildErrorMessageFrom(e.getBindingResult()));
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ApiError handleMissingServletRequestParameterException(
      MissingServletRequestParameterException e) {
    log.warn(BAD_REQUEST_LOG_MSG, e);
    return new SimpleApiError(e.getMessage());
  }

  private String buildErrorMessageFrom(BindingResult bindingResult) {
    return Stream.concat(
            bindingResult.getFieldErrors().stream()
                .map(e -> formatError("Field", e.getField(), e.getDefaultMessage())),
            bindingResult.getGlobalErrors().stream()
                .map(e -> formatError("Object", e.getObjectName(), e.getDefaultMessage()))
        )
        .sorted()
        .collect(Collectors.joining("\n", "Following validation errors occurred:\n", ""));
  }

  private String formatError(String sourceType, String sourceName, String message) {
    return String.format(
        "%s: '%s', message: '%s'",
        sourceType,
        sourceName,
        Optional.ofNullable(message).orElse("Not provided").split(NESTED_EXCEPTION_PREFIX)[0]
    );
  }
}
