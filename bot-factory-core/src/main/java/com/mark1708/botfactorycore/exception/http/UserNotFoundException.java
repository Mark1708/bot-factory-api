package com.mark1708.botfactorycore.exception.http;

import java.util.Collection;
import java.util.List;
import lombok.Getter;

@Getter
public class UserNotFoundException extends ResourceNotFoundException {

  private final ResourceType type = ResourceType.USER;
  private final UserSearchType searchType;
  private Collection<Long> ids;
  private Collection<String> usernames;
  private Collection<String> emails;
  private Collection<String> unrecognizedValues;

  public UserNotFoundException(UserSearchType searchType, Long id) {
    super(ResourceType.USER, List.of());
    this.searchType = searchType;
    this.ids = List.of(id);
  }

  public UserNotFoundException(UserSearchType searchType, String value) {
    super(ResourceType.USER, List.of());
    this.searchType = searchType;
    switch (searchType) {
      case EMAIL:
        this.emails = List.of(value);
        break;
      case USERNAME:
        this.usernames = List.of(value);
        break;
      default:
        this.unrecognizedValues = List.of(value);
    }
  }

  public UserNotFoundException(UserSearchType searchType, List<?> values) {
    super(ResourceType.USER, List.of());
    this.searchType = searchType;
    switch (searchType) {
      case ID:
        this.ids = (List<Long>) values;
        break;
      case EMAIL:
        this.emails = (List<String>) values;
        break;
      case USERNAME:
        this.usernames = (List<String>) values;
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
      case EMAIL:
        return String.format("Failed to find [%s] with emails [%s]", type, emails);
      case USERNAME:
        return String.format("Failed to find [%s] with usernames [%s]", type, usernames);
      default:
        return String.format("Failed to find [%s] with unrecognized values [%s]", type,
            unrecognizedValues);
    }
  }

  public static enum UserSearchType {
    ID,
    USERNAME,
    EMAIL,
    UNRECOGNIZED_SEARCH_TYPE
  }

}
