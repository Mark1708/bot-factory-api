package com.mark1708.tracker.exception.http;

import lombok.Getter;

@Getter
public enum QueryType {
  ID("ids"),
  BOT_ID("bot_ids"),
  USER_ID("user_ids"),
  BOT_ID_AND_LINK("bot_ids/link"),
  BOT_ID_AND_SLUG("bot_ids/slug"),
  NAME("names"),
  ;

  private final String queryName;
  QueryType(String queryName) {
    this.queryName = queryName;
  }
}
