package com.phonenumberinput;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;

public class PhoneNumberToolbarView extends Toolbar {
  private TextView rightTextView;


  public PhoneNumberToolbarView(Context context) {
    super(context);
    init();
  }

  public PhoneNumberToolbarView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public PhoneNumberToolbarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    rightTextView = new TextView(getContext());
    rightTextView.setGravity(Gravity.END);
    addView(rightTextView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.END));
  }

  public void setDoneButtonText(String text) {
    rightTextView.setText(text);
  }
}
