package com.mark1708.storageservice.exception.http;

import com.mark1708.storageservice.exception.StorageException;
import java.util.Collection;
import java.util.List;
import lombok.Getter;

@Getter
public class ResourceNotFoundException extends StorageException {

  private final ResourceType type;
  private final QueryType queryType;
  private final Collection<String> values;

  public ResourceNotFoundException(ResourceType type, QueryType queryType, Object value) {
    this(type, queryType, List.of(value.toString()));
  }

  public ResourceNotFoundException(ResourceType type, QueryType queryType, String value) {
    this(type, queryType, List.of(value));
  }

  public ResourceNotFoundException(ResourceType type, QueryType queryType, Collection<String> values) {
    this.type = type;
    this.queryType = queryType;
    this.values = List.copyOf(values);
  }

  @Override
  public String getMessage() {
    return String.format("Failed to find [%s] with %s [%s]", type, queryType.getQueryName(), values);
  }

}
