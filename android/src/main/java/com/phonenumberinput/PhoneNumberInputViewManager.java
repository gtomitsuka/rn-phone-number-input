package com.phonenumberinput;

import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.SelectionPredicates;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StorageStrategy;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.ArrayList;
import java.util.List;

@ReactModule(name = PhoneNumberInputViewManager.NAME)
public class PhoneNumberInputViewManager
  extends PhoneNumberInputViewManagerSpec<PhoneNumberInputView> {

  public static final String NAME = "PhoneNumberInputView";

  @Override
  public String getName() {
    return NAME;
  }

  @NonNull
  @Override
  public PhoneNumberInputView createViewInstance(@NonNull ThemedReactContext context) {
    PhoneNumberInputView view = new PhoneNumberInputView(context);

    LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
    view.setLayoutManager(layoutManager);

    CountryPickerAdapter adapter = new CountryPickerAdapter(view.getContext(), new ArrayList<>(), layoutManager);
    view.setAdapter(adapter);

    return view;
  }

  @Override
  @ReactProp(name = "items")
  public void setItems(PhoneNumberInputView view, @Nullable ReadableArray a) {
    List<Country> countries = new ArrayList<>();

    if (a != null) {
      for (int i = 0; i < a.size(); i++) {
        ReadableMap map = a.getMap(i);

        String countryName = map.getString("name");
        String countryEmoji = map.getString("emoji");
        String countryCode = map.getString("code");
        String callingCode = map.getString("tel");

        Country country = new Country(countryName, countryCode, countryEmoji, callingCode);
        countries.add(country);
      }
    }

    CountryPickerAdapter adapter = (CountryPickerAdapter) view.getAdapter();

    if (adapter != null) {
      adapter.setCountries(countries);
    } else {
      LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
      view.setLayoutManager(layoutManager);

      LinearSnapHelper snapHelper = new LinearSnapHelper();
      snapHelper.attachToRecyclerView(view);

      adapter = new CountryPickerAdapter(view.getContext(), countries, layoutManager);
      view.setAdapter(adapter);
    }

    // Set up the SelectionTracker
    SelectionTracker<String> selectionTracker = new SelectionTracker.Builder<>(
      "countryPickerSelection",
      view,
      new CountryKeyProvider(1, countries),
      new CountryDetailsLookup(view),
      StorageStrategy.createStringStorage())
      .withSelectionPredicate(SelectionPredicates.createSelectSingleAnything())
      .build();

    adapter.setSelectionTracker(selectionTracker);
  }

  @Override
  @ReactProp(name = "darkMode")
  public void setDarkMode(PhoneNumberInputView view, boolean darkMode) {
    int backgroundColor = darkMode ? Color.parseColor("#616161") : Color.parseColor("#FFFFFF");
    view.setBackgroundColor(backgroundColor);

    CountryPickerAdapter adapter = (CountryPickerAdapter) view.getAdapter();

    if (adapter != null) {
      adapter.setDarkMode(darkMode);
    }
  }

  @Override
  @ReactProp(name = "selectedIndex")
  public void setSelectedIndex(PhoneNumberInputView view, int index) {
    CountryPickerAdapter adapter = (CountryPickerAdapter)view.getAdapter();

    if (adapter != null) {
      adapter.setSelectedIndex(index);
    }
  }
}
