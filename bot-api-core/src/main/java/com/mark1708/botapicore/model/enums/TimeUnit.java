package com.mark1708.botapicore.model.enums;

import lombok.Getter;

@Getter
public enum TimeUnit {
  SECOND(1),
  MINUTE(2),
  HOUR(3),
  DAY(4),
  MONTH(5),
  YEAR(6)
  ;

  private final int value;

  TimeUnit(int value) {
    this.value = value;
  }
}
