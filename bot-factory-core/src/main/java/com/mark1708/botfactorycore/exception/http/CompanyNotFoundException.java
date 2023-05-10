package com.mark1708.botfactorycore.exception.http;

import java.util.Collection;
import java.util.List;
import lombok.Getter;

@Getter
public class CompanyNotFoundException extends ResourceNotFoundException {

  private final ResourceType type = ResourceType.COMPANY;
  private final CompanySearchType searchType;
  private Collection<Long> ids;
  private Collection<String> slugs;
  private Collection<String> unrecognizedValues;

  public CompanyNotFoundException(CompanySearchType searchType, Long id) {
    super(ResourceType.COMPANY, List.of());
    this.searchType = searchType;
    this.ids = List.of(id);
  }

  public CompanyNotFoundException(CompanySearchType searchType, String value) {
    super(ResourceType.COMPANY, List.of());
    this.searchType = searchType;
    switch (searchType) {
      case SLUG:
        this.slugs = List.of(value);
        break;
      default:
        this.unrecognizedValues = List.of(value);
    }
  }

  public CompanyNotFoundException(CompanySearchType searchType, List<?> values) {
    super(ResourceType.COMPANY, List.of());
    this.searchType = searchType;
    switch (searchType) {
      case ID:
        this.ids = (List<Long>) values;
        break;
      case SLUG:
        this.slugs = (List<String>) values;
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
      case SLUG:
        return String.format("Failed to find [%s] with slugs [%s]", type, slugs);
      default:
        return String.format("Failed to find [%s] with unrecognized values [%s]", type,
            unrecognizedValues);
    }
  }

  public static enum CompanySearchType {
    ID,
    SLUG,
    UNRECOGNIZED_SEARCH_TYPE
  }

}
