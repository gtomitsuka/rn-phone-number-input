import {
  StyleSheet,
  Text,
  TextStyle,
  TouchableOpacity,
  ViewStyle,
} from 'react-native';
import React from 'react';
import type { InputManager } from './usePhoneNumberInput';
import { countries } from './countries';

interface CountryPickerFieldProps {
  manager: InputManager;
  fieldStyles?: ViewStyle;
  textStyles?: TextStyle;
}

const CountryPickerField = ({
  manager,
  fieldStyles,
  textStyles,
}: CountryPickerFieldProps) => {
  const { state, dispatch } = manager;

  let country;
  if (state.customCountries) {
    country = state.customCountries.find((c) => c.code === state.countryCode);
  } else {
    country = countries.find((c) => c.code === state.countryCode);
  }

  if (country == null) {
    throw new Error('Invalid country');
  }

  return (
    <TouchableOpacity
      style={{ ...fieldStyles }}
      onPress={() =>
        dispatch({ type: 'setHidden', payload: !state.pickerHidden })
      }
    >
      {/* textStyles applies to all texts */}
      <Text style={{ ...styles.text, ...textStyles }}>
        {country.emoji} {state.countryTel}
      </Text>
      <Text style={{ ...styles.chevron, ...textStyles }}>▼</Text>
    </TouchableOpacity>
  );
};

const styles = StyleSheet.create({
  text: {
    fontSize: 20,
  },
  chevron: {
    fontSize: 14,
  },
});

export default CountryPickerField;
