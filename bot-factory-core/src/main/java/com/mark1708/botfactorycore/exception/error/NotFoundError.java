package com.mark1708.botfactorycore.exception.error;

import com.mark1708.botfactorycore.exception.http.ResourceType;
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
  private final Collection<K> ids;

  public NotFoundError(ResourceType resourceType, Collection<K> identifiers, String message) {
    super(message);
    this.resourceType = resourceType;
    this.ids = identifiers;
  }
}
