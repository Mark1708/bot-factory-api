package com.mark1708.notification.model.enums;

import java.util.stream.Stream;

public enum Messenger {
  TELEGRAM(1)
  ;

  private int messenger;

  Messenger(int messenger) {
    this.messenger = messenger;
  }

  public int getMessenger() {
    return messenger;
  }

  public static Messenger of(int messenger) {
    return Stream.of(Messenger.values())
        .filter(m -> m.getMessenger() == messenger)
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }
}
