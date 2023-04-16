package com.phonenumberinput;

import android.graphics.Color;

import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

@ReactModule(name = PhoneNumberInputViewManager.NAME)
public class PhoneNumberInputViewManager extends PhoneNumberInputViewManagerSpec<PhoneNumberInputView> {

  public static final String NAME = "PhoneNumberInputView";

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public PhoneNumberInputView createViewInstance(ThemedReactContext context) {
    return new PhoneNumberInputView(context);
  }

  @Override
  @ReactProp(name = "items")
  public void setItems(PhoneNumberInputView view, @Nullable ReadableArray a) {

  }

  @Override
  @ReactProp(name = "darkMode")
  public void setDarkMode(PhoneNumberInputView view, boolean darkMode) {

  }

  @Override
  @ReactProp(name = "selectedIndex")
  public void setSelectedIndex(PhoneNumberInputView view, int index) {

  }
}
