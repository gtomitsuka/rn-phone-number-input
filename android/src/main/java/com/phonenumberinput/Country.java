package com.phonenumberinput;

public class Country {
  private final String name; // human-readable name
  private final String code; // ISO-3601 code
  private final String emoji; // unicode emoji
  private final String callingCode; // calling code, e.g. "+1"

  public Country(String name, String code, String emoji, String callingCode) {
    this.name = name;
    this.code = code;
    this.emoji = emoji;
    this.callingCode = callingCode;
  }

  public String getName() {
    return name;
  }

  public String getCode() {
    return code;
  }

  public String getEmoji() {
    return emoji;
  }

  public String getCallingCode() {
    return callingCode;
  }
}
