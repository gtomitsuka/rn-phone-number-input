package com.rnphonenumberinput;

import android.view.View;

import androidx.annotation.Nullable;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.viewmanagers.RnPhoneNumberInputViewManagerDelegate;
import com.facebook.react.viewmanagers.RnPhoneNumberInputViewManagerInterface;
import com.facebook.soloader.SoLoader;

public abstract class RnPhoneNumberInputViewManagerSpec<T extends View> extends SimpleViewManager<T> implements RnPhoneNumberInputViewManagerInterface<T> {
  static {
    if (BuildConfig.CODEGEN_MODULE_REGISTRATION != null) {
      SoLoader.loadLibrary(BuildConfig.CODEGEN_MODULE_REGISTRATION);
    }
  }

  private final ViewManagerDelegate<T> mDelegate;

  public RnPhoneNumberInputViewManagerSpec() {
    mDelegate = new RnPhoneNumberInputViewManagerDelegate(this);
  }

  @Nullable
  @Override
  protected ViewManagerDelegate<T> getDelegate() {
    return mDelegate;
  }
}
