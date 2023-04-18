package com.phonenumberinput;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CountryPickerAdapter extends RecyclerView.Adapter<CountryPickerListItem> {
  private List<Country> countries;
  private final LayoutInflater inflater;
  private boolean darkMode;
  private SelectionTracker<String> selectionTracker;

  public CountryPickerAdapter(Context context, List<Country> countries) {
    this.countries = countries;
    this.inflater = LayoutInflater.from(context);
  }

  public void setSelectionTracker(SelectionTracker<String> selectionTracker) {
    this.selectionTracker = selectionTracker;
  }

  public void setCountries(List<Country> countries) {
    this.countries = countries;
  }

  @SuppressLint("NotifyDataSetChanged")
  public void setDarkMode(boolean darkMode) {
    this.darkMode = darkMode;

    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public CountryPickerListItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = inflater.inflate(R.layout.country_picker_list_item, parent, false);
    return new CountryPickerListItem(view);
  }

  @Override
  public void onBindViewHolder(@NonNull CountryPickerListItem holder, int position) {
    Country country = countries.get(position);

    // Set the country flag, country name, and calling code for the current list item
    holder.countryName.setText(String.format("%s   %s", country.getEmoji(), country.getName()));
    holder.callingCode.setText(country.getCallingCode());

    int textColor = darkMode ? Color.parseColor("#FFFFFF") : Color.parseColor("#000000");
    holder.countryName.setTextColor(textColor);
    holder.callingCode.setTextColor(textColor);

    holder.bind(country);
    holder.itemView.setActivated(selectionTracker.isSelected(country.getCode()));
  }

  @Override
  public int getItemCount() {
    return countries.size();
  }
}
