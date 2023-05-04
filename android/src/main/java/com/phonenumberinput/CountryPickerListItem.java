package com.phonenumberinput;

import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.widget.RecyclerView;

import java.util.function.Consumer;

public class CountryPickerListItem extends RecyclerView.ViewHolder {
  TextView countryName; // includes emoji
  TextView callingCode;

  Country country;

  @RequiresApi(api = Build.VERSION_CODES.N)
  public CountryPickerListItem(@NonNull View itemView, Consumer<Integer> countryClickListener) {
    super(itemView);

    countryName = itemView.findViewById(R.id.country_name);
    callingCode = itemView.findViewById(R.id.calling_code);

    itemView.setOnClickListener(l -> {
      System.out.println("called");
      int position = getBindingAdapterPosition();
      if (position != RecyclerView.NO_POSITION) {
        countryClickListener.accept(position);
      }
    });
  }

  public void bind(Country country) {
    this.country = country;
    // Bind the country data to your views
    // ...
  }

  public void highlight(boolean isSelected, boolean darkMode) {
    if (isSelected) {
      int color = darkMode ? Color.parseColor("#7a7a7a") : Color.parseColor("#d9d9d9");
      itemView.setBackgroundColor(color);
    } else {
      itemView.setBackgroundColor(Color.TRANSPARENT);
    }
  }

  public ItemDetailsLookup.ItemDetails<String> getItemDetails() {
    return new ItemDetailsLookup.ItemDetails<String>() {
      @Override
      public int getPosition() {
        return getBindingAdapterPosition();
      }

      @Nullable
      @Override
      public String getSelectionKey() {
        return country.getCode();
      }
    };
  }
}
