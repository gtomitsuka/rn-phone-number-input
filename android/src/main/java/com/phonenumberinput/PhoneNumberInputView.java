package com.phonenumberinput;

import androidx.annotation.Nullable;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

public class PhoneNumberInputView extends RecyclerView {
  private Context context;

  public PhoneNumberInputView(Context context) {
    super(context);
    init(context);
  }

  public PhoneNumberInputView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public PhoneNumberInputView(Context context,
                              @Nullable AttributeSet attrs,
                              int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private void init(Context _context) {
    context = _context;
    addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
    LinearSnapHelper snapHelper = new LinearSnapHelper();
    snapHelper.attachToRecyclerView(this);
  }
}
