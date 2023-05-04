import React from 'react';
import NativePhoneNumberInputView from './PhoneNumberInputViewNativeComponent';
import { StyleSheet, ViewStyle } from 'react-native';
import { countries as defaultCountries } from './countries';
import type { Country } from './types';
import { COUNTRY_PICKER_INPUT_HEIGHT } from './consts';

type InputViewProps = {
  countries?: Country[];
  style?: ViewStyle;
  onChange?: (country: { tel: string; code: string }) => void;
  darkMode?: boolean;
  // in ISO format (e.g., "UK") due to ambiguity for certain codes
  defaultCountry?: string;
};
const CountryPicker = (props: InputViewProps) => {
  const _countries = props.countries ? props.countries : defaultCountries;
  return (
    <NativePhoneNumberInputView
      items={_countries}
      onChange={(e) =>
        props.onChange &&
        props.onChange({
          tel: e.nativeEvent.newValue,
          code: e.nativeEvent.newCode,
        })
      }
      darkMode={props.darkMode ? props.darkMode : false}
      selectedIndex={_countries.findIndex(
        (country) => country.code === props.defaultCountry
      )}
      style={{
        ...styles.nativeInput,
        ...props.style,
      }}
    />
  );
};

const styles = StyleSheet.create({
  nativeInput: {
    width: '100%',
    height: COUNTRY_PICKER_INPUT_HEIGHT,
    overflow: 'hidden',
  },
});

export default CountryPicker;
