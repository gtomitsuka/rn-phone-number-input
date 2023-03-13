package com.rnphonenumberinput;

import android.graphics.Color;

import androidx.annotation.Nullable;

import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

@ReactModule(name = RnPhoneNumberInputViewManager.NAME)
public class RnPhoneNumberInputViewManager extends RnPhoneNumberInputViewManagerSpec<RnPhoneNumberInputView> {

  public static final String NAME = "RnPhoneNumberInputView";

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public RnPhoneNumberInputView createViewInstance(ThemedReactContext context) {
    return new RnPhoneNumberInputView(context);
  }

  @Override
  @ReactProp(name = "color")
  public void setColor(RnPhoneNumberInputView view, @Nullable String color) {
    view.setBackgroundColor(Color.parseColor(color));
  }
}
