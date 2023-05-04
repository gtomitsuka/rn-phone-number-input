package com.phonenumberinput;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CountryPickerAdapter extends RecyclerView.Adapter<CountryPickerListItem> {
  private List<Country> countries;
  private final LayoutInflater inflater;
  private boolean darkMode;
  private SelectionTracker<String> selectionTracker;
  private int defaultCountry;
  private final LinearLayoutManager layoutManager;

  public CountryPickerAdapter(Context context,
                              List<Country> countries,
                              LinearLayoutManager layoutManager) {
    this.countries = countries;
    this.inflater = LayoutInflater.from(context);
    this.layoutManager = layoutManager;
  }

  public void onCountryClick(int index) {
    setSelectedIndex(index);
    notifyItemChanged(index);
  }

  public void setSelectionTracker(SelectionTracker<String> selectionTracker) {
    this.selectionTracker = selectionTracker;

    if (defaultCountry >= 0 && defaultCountry < countries.size()) {
      String countryCode = countries.get(defaultCountry).getCode();
      selectionTracker.select(countryCode);
    } else {
      selectionTracker.clearSelection();
    }
  }

  public void setCountries(List<Country> countries) {
    this.countries = countries;

    if (selectionTracker == null) {
      return;
    }

    if (defaultCountry >= 0 && defaultCountry < countries.size()) {
      String countryCode = countries.get(defaultCountry).getCode();
      selectionTracker.select(countryCode);
    } else {
      selectionTracker.clearSelection();
    }
  }

  @SuppressLint("NotifyDataSetChanged")
  public void setDarkMode(boolean darkMode) {
    this.darkMode = darkMode;

    notifyDataSetChanged();
  }

  @RequiresApi(api = Build.VERSION_CODES.N)
  @NonNull
  @Override
  public CountryPickerListItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = inflater.inflate(R.layout.country_picker_list_item, parent, false);
    return new CountryPickerListItem(view, this::onCountryClick);
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
    boolean isSelected = selectionTracker.isSelected(country.getCode());
    holder.itemView.setActivated(isSelected);
    holder.highlight(isSelected, darkMode);
  }

  @Override
  public int getItemCount() {
    return countries.size();
  }

  public void setSelectedIndex(int selectedIndex) {
    defaultCountry = selectedIndex;

    if (selectionTracker == null) {
      return;
    }

    if (selectedIndex >= 0 && selectedIndex < countries.size()) {
      String countryCode = countries.get(selectedIndex).getCode();
      selectionTracker.select(countryCode);

      layoutManager.scrollToPosition(selectedIndex);
    } else {
      selectionTracker.clearSelection();
    }
  }
}
