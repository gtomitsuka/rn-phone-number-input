package com.phonenumberinput;

import android.view.View;

import androidx.annotation.Nullable;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.viewmanagers.PhoneNumberToolbarViewManagerDelegate;
import com.facebook.react.viewmanagers.PhoneNumberToolbarViewManagerInterface;
import com.facebook.soloader.SoLoader;

public abstract class PhoneNumberToolbarViewManagerSpec<T extends View> extends SimpleViewManager<T> implements PhoneNumberToolbarViewManagerInterface<T> {
  static {
    if (BuildConfig.CODEGEN_MODULE_REGISTRATION != null) {
      SoLoader.loadLibrary(BuildConfig.CODEGEN_MODULE_REGISTRATION);
    }
  }

  private final ViewManagerDelegate<T> mDelegate;

  public PhoneNumberToolbarViewManagerSpec() {
    mDelegate = new PhoneNumberToolbarViewManagerDelegate(this);
  }

  @Nullable
  @Override
  protected ViewManagerDelegate<T> getDelegate() {
    return mDelegate;
  }
}
