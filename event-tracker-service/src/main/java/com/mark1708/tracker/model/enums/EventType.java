package com.mark1708.tracker.model.enums;

import java.util.stream.Stream;

public enum EventType {

  CLICKER("clicker", 1),
  UTM("utm", 2);

  private final String name;
  private final Integer type;

  EventType(String name, Integer value) {
    this.name = name;
    this.type = value;
  }

  public static EventType of(int value) {
    return Stream.of(EventType.values())
        .filter(p -> p.getValue() == value)
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }

  public String getName() {
    return name;
  }

  public Integer getValue() {
    return type;
  }
}
