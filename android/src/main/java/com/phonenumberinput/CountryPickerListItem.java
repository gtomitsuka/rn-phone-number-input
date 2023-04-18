package com.phonenumberinput;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.widget.RecyclerView;

public class CountryPickerListItem extends RecyclerView.ViewHolder {
  TextView countryName; // includes emoji
  TextView callingCode;

  Country country;

  public CountryPickerListItem(View itemView) {
    super(itemView);
    countryName = itemView.findViewById(R.id.country_name);
    callingCode = itemView.findViewById(R.id.calling_code);
  }

  public void bind(Country country) {
    this.country = country;
    // Bind the country data to your views
    // ...
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
