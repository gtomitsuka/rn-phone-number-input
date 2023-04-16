package com.phonenumberinput;

import android.view.View;

import androidx.annotation.Nullable;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.viewmanagers.PhoneNumberInputViewManagerDelegate;
import com.facebook.react.viewmanagers.PhoneNumberInputViewManagerInterface;
import com.facebook.soloader.SoLoader;

public abstract class PhoneNumberInputViewManagerSpec<T extends View> extends SimpleViewManager<T> implements PhoneNumberInputViewManagerInterface<T> {
  static {
    if (BuildConfig.CODEGEN_MODULE_REGISTRATION != null) {
      SoLoader.loadLibrary(BuildConfig.CODEGEN_MODULE_REGISTRATION);
    }
  }

  private final ViewManagerDelegate<T> mDelegate;

  public PhoneNumberInputViewManagerSpec() {
    mDelegate = new PhoneNumberInputViewManagerDelegate(this);
  }

  @Nullable
  @Override
  protected ViewManagerDelegate<T> getDelegate() {
    return mDelegate;
  }
}
