package com.phonenumberinput;

import androidx.annotation.Nullable;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.RecyclerView;

public class PhoneNumberInputView extends RecyclerView {

  public PhoneNumberInputView(Context context) {
    super(context);
  }

  public PhoneNumberInputView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public PhoneNumberInputView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public void setDarkMode(boolean darkMode) {
    
  }
}
