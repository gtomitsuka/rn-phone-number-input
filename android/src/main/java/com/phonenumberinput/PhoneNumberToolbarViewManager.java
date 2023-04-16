package com.phonenumberinput;

import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

@ReactModule(name = PhoneNumberToolbarViewManager.NAME)
public class PhoneNumberToolbarViewManager extends PhoneNumberToolbarViewManagerSpec<PhoneNumberToolbarView> {

  public static final String NAME = "PhoneNumberToolbarView";

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public PhoneNumberToolbarView createViewInstance(ThemedReactContext context) {
    return new PhoneNumberToolbarView(context);
  }

  @Override
  @ReactProp(name = "doneButtonText")
  public void setDoneButtonText(PhoneNumberToolbarView view, @Nullable String text) {

  }

  @Override
  @ReactProp(name = "darkMode")
  public void setDarkMode(PhoneNumberToolbarView view, boolean darkMode) {

  }
}
