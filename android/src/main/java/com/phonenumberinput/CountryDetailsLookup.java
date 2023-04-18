package com.phonenumberinput;

import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.widget.RecyclerView;

import com.phonenumberinput.CountryPickerListItem;

public class CountryDetailsLookup extends ItemDetailsLookup<String> {
  private final RecyclerView recyclerView;

  public CountryDetailsLookup(RecyclerView recyclerView) {
    this.recyclerView = recyclerView;
  }

  @Nullable
  @Override
  public ItemDetails<String> getItemDetails(@NonNull MotionEvent e) {
    View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
    if (view != null) {
      RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(view);
      if (viewHolder instanceof CountryPickerListItem) {
        return ((CountryPickerListItem) viewHolder).getItemDetails();
      }
    }
    return null;
  }
}
