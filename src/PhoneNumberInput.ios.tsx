import React from 'react';
import NativePhoneNumberInputView from './PhoneNumberInputViewNativeComponent';
import { StyleSheet, ViewStyle } from 'react-native';
import { countries as defaultCountries } from './countries';
import type { Country } from './types';
import { COUNTRY_PICKER_INPUT_HEIGHT } from './consts';

type InputViewProps = {
  countries?: Country[];
  style?: ViewStyle;
  onChange?: (countryCode: string) => void;
  darkMode?: boolean;
  defaultCountry?: string;
};
const PhoneNumberInput = (props: InputViewProps) => {
  const _countries = props.countries ? props.countries : defaultCountries;
  return (
    <NativePhoneNumberInputView
      items={_countries}
      onChange={(e) => props.onChange && props.onChange(e.nativeEvent.newValue)}
      darkMode={props.darkMode ? props.darkMode : false}
      selectedIndex={_countries.findIndex(
        (country) =>
          country.name ===
          (props.defaultCountry ? props.defaultCountry : 'United States')
      )} // default country is the US
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
  },
});

export default PhoneNumberInput;
