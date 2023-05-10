package com.mark1708.botfactorycore.exception.http;

import com.mark1708.botfactorycore.exception.BotFactoryException;
import java.util.Collection;
import java.util.List;
import lombok.Getter;

@Getter
public class ResourceNotFoundException extends BotFactoryException {

  private final ResourceType type;
  private final Collection<Long> ids;

  public ResourceNotFoundException(ResourceType type, long id) {
    this.type = type;
    this.ids = List.of(id);
  }

  public ResourceNotFoundException(ResourceType type, Collection<Long> ids) {
    this.type = type;
    this.ids = List.copyOf(ids);
  }

  @Override
  public String getMessage() {
    return String.format("Failed to find [%s] with ids [%s]", type, ids);
  }

}
