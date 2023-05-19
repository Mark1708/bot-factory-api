package com.mark1708.botfactorycore.exception.http;

import java.util.Collection;
import java.util.List;
import lombok.Getter;

@Getter
public class RoleNotFoundException extends ResourceNotFoundException {

  private final ResourceType type = ResourceType.ROLE;
  private final RoleSearchType searchType;
  private Collection<Long> ids;
  private Collection<String> names;
  private Collection<String> unrecognizedValues;

  public RoleNotFoundException(RoleSearchType searchType, Long id) {
    super(ResourceType.ROLE, List.of());
    this.searchType = searchType;
    this.ids = List.of(id);
  }

  public RoleNotFoundException(RoleSearchType searchType, String value) {
    super(ResourceType.ROLE, List.of());
    this.searchType = searchType;
    switch (searchType) {
      case NAME:
        this.names = List.of(value);
        break;
      default:
        this.unrecognizedValues = List.of(value);
    }
  }

  public RoleNotFoundException(RoleSearchType searchType, List<?> values) {
    super(ResourceType.ROLE, List.of());
    this.searchType = searchType;
    switch (searchType) {
      case ID:
        this.ids = (List<Long>) values;
        break;
      case NAME:
        this.names = (List<String>) values;
        break;
      default:
        this.unrecognizedValues = (List<String>) values;
    }
  }

  @Override
  public String getMessage() {
    switch (searchType) {
      case ID:
        return String.format("Failed to find [%s] with ids [%s]", type, ids);
      case NAME:
        return String.format("Failed to find [%s] with names [%s]", type, names);
      default:
        return String.format("Failed to find [%s] with unrecognized values [%s]", type,
            unrecognizedValues);
    }
  }

  public static enum RoleSearchType {
    ID,
    NAME,
    UNRECOGNIZED_SEARCH_TYPE
  }

}
