package com.mark1708.botfactorycore.exception.error;

import com.mark1708.botfactorycore.exception.http.ResourceType;
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
  private final Collection<T> ids;

  public ForbiddenError(ResourceType resourceType, Collection<T> identifiers, String message) {
    super(message);
    this.resourceType = resourceType;
    this.ids = identifiers;
  }
}
