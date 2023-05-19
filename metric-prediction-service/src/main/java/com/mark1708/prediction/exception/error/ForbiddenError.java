package com.mark1708.prediction.exception.error;

import com.mark1708.prediction.exception.http.QueryType;
import com.mark1708.prediction.exception.http.ResourceType;
import java.util.Collection;
import lombok.Getter;

/**
 * Ошибка получения доступа к запрещенному ресурсу.
 *
 * @param <T> тип идентификатора
 */
@Getter
public class ForbiddenError<T> extends SimpleApiError {

  private final ResourceType resourceType;
  private final QueryType queryType;
  private final Collection<T> query;

  public ForbiddenError(ResourceType resourceType, QueryType queryType, Collection<T> identifiers, String message) {
    super(message);
    this.resourceType = resourceType;
    this.queryType = queryType;
    this.query = identifiers;
  }
}
