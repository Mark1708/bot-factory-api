package com.mark1708.storageservice.exception.error;

import com.mark1708.storageservice.exception.error.SimpleApiError;
import com.mark1708.storageservice.exception.http.QueryType;
import com.mark1708.storageservice.exception.http.ResourceType;
import java.util.Collection;
import lombok.Getter;

/**
 * Ошибка отсутствия ресурса.
 *
 * @param <K> тип идентификатора
 */
@Getter
public class NotFoundError<K> extends SimpleApiError {

  private final ResourceType resourceType;
  private final QueryType queryType;
  private final Collection<K> ids;

  public NotFoundError(ResourceType resourceType, QueryType queryType, Collection<K> identifiers, String message) {
    super(message);
    this.resourceType = resourceType;
    this.queryType = queryType;
    this.ids = identifiers;
  }
}
