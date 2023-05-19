package com.mark1708.prediction.exception.http;

import lombok.Getter;

@Getter
public enum QueryType {
  ID("ids"),
  BOT_ID("bots ids"),
  ;

  private final String queryName;
  QueryType(String queryName) {
    this.queryName = queryName;
  }
}
