package com.mark1708.botapicore.exception.http;

import com.mark1708.botapicore.exception.BotApiException;
import java.util.Collection;
import java.util.List;
import lombok.Getter;

/**
 * Доступ к ресурсу запрещен.
 */
@Getter
public class ResourceForbiddenException extends BotApiException {

  private final ResourceType type;
  private final QueryType queryType;
  private final Collection<Long> values;

  public ResourceForbiddenException(ResourceType type, QueryType queryType, long values) {
    this(type, queryType, List.of(values));
  }

  public ResourceForbiddenException(ResourceType type, QueryType queryType, Collection<Long> values) {
    this.type = type;
    this.values = values;
    this.queryType = queryType;
  }

  @Override
  public String getMessage() {
    return String.format("Unable to access [%s] with %s %s", type, queryType.getQueryName(), values);
  }

}
