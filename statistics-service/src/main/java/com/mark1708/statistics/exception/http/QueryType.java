package com.mark1708.statistics.exception.http;

import lombok.Getter;

@Getter
public enum QueryType {
  ID("ids"),
  SERVICE_ID("service ids"),
  BOT_ID("bot ids"),
  TARIFF_ID("tariff ids"),
  ;

  private final String queryName;
  QueryType(String queryName) {
    this.queryName = queryName;
  }
}
