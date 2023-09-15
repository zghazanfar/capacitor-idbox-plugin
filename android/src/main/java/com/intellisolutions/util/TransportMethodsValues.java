package org.apache.cordova.Idbox_Plugin.util;

public enum TransportMethodsValues {
  ALL(0),
  WEBSOCKETS(1),
  LONG_POLLING(2);

  private final int value;

  TransportMethodsValues(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

}
