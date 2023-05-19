package com.mark1708.botfactorycore.exception.http;

import com.mark1708.botfactorycore.exception.BotFactoryException;
import java.util.Collection;
import java.util.List;
import lombok.Getter;

/**
 * Доступ к ресурсу запрещен.
 */
@Getter
public class ResourceForbiddenException extends BotFactoryException {

  private final ResourceType type;
  private final Collection<Long> ids;

  public ResourceForbiddenException(ResourceType type, long id) {
    this(type, List.of(id));
  }

  public ResourceForbiddenException(ResourceType type, Collection<Long> ids) {
    this.type = type;
    this.ids = ids;
  }

  @Override
  public String getMessage() {
    return String.format("Unable to access [%s] with ids %s", type, ids);
  }

}
