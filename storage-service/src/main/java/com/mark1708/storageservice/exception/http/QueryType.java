package com.mark1708.storageservice.exception.http;

import lombok.Getter;

@Getter
public enum QueryType {
  ID("ids"),
  BOT_ID_AND_SLUG("bot IDs and slugs"),
  COMPANY_ID("company IDs"),
  FILE_DATA_ID("fileData IDs"),
  TOKEN("tokens"),
  ;

  private final String queryName;
  QueryType(String queryName) {
    this.queryName = queryName;
  }
}
