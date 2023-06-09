package com.mark1708.botapicore.exception.http;

import lombok.Getter;

@Getter
public enum QueryType {
  ID("ids"),
  API_KEY("api-keys"),
  PLATFORM_ID("platform_ids"),
  NAME("name"),
  ;

  private final String queryName;
  QueryType(String queryName) {
    this.queryName = queryName;
  }
}
