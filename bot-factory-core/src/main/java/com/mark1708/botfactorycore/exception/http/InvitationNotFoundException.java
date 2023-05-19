package com.mark1708.botfactorycore.exception.http;

import java.util.Collection;
import java.util.List;
import lombok.Getter;

@Getter
public class InvitationNotFoundException extends ResourceNotFoundException {

  private final ResourceType type = ResourceType.INVITATION;
  private final InvitationSearchType searchType;
  private Collection<Long> ids;
  private Collection<String> emailWithCode;
  private Collection<String> unrecognizedValues;

  public InvitationNotFoundException(InvitationSearchType searchType, Long id) {
    super(ResourceType.INVITATION, List.of());
    this.searchType = searchType;
    this.ids = List.of(id);
  }

  public InvitationNotFoundException(InvitationSearchType searchType, String value) {
    super(ResourceType.INVITATION, List.of());
    this.searchType = searchType;
    switch (searchType) {
      case EMAIL_CODE:
        this.emailWithCode = List.of(value);
        break;
      default:
        this.unrecognizedValues = List.of(value);
    }
  }

  public InvitationNotFoundException(InvitationSearchType searchType, List<?> values) {
    super(ResourceType.INVITATION, List.of());
    this.searchType = searchType;
    switch (searchType) {
      case ID:
        this.ids = (List<Long>) values;
        break;
      case EMAIL_CODE:
        this.emailWithCode = (List<String>) values;
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
      case EMAIL_CODE:
        return String.format("Failed to find [%s] with slugs [%s]", type, emailWithCode);
      default:
        return String.format("Failed to find [%s] with unrecognized values [%s]", type,
            unrecognizedValues);
    }
  }

  public static enum InvitationSearchType {
    ID,
    EMAIL_CODE,
    UNRECOGNIZED_SEARCH_TYPE
  }

}
