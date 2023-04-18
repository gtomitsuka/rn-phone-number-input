package com.phonenumberinput;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemKeyProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CountryKeyProvider extends ItemKeyProvider<String> {
  private final List<Country> countries;

  public CountryKeyProvider(int scope, List<Country> countries) {
    super(scope);
    this.countries = countries;
  }

  @Nullable
  @Override
  public String getKey(int position) {
    return countries.get(position).getCode();
  }

  @Override
  public int getPosition(@NonNull String key) {
    for (int i = 0; i < countries.size(); i++) {
      if (countries.get(i).getCode().equals(key)) {
        return i;
      }
    }
    return RecyclerView.NO_POSITION;
  }
}
