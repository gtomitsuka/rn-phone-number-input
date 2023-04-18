/* eslint-disable react-hooks/exhaustive-deps */

import React, { useEffect, useRef } from 'react';
import { Animated, StyleSheet, ViewStyle } from 'react-native';
import { CountryPicker } from './index';
import { COUNTRY_PICKER_VIEW_HEIGHT } from './consts';
import type { InputManager } from './usePhoneNumberInput';
import NativeToolbar from './NativeToolbar';

interface CountryPickerProps {
  manager: InputManager;
  doneButtonText?: string;
  toolbarStyles?: ViewStyle;
  pickerStyles?: ViewStyle;
}

const CountryPickerModal = ({
  manager,
  doneButtonText,
  toolbarStyles,
  pickerStyles,
}: CountryPickerProps) => {
  const { state, dispatch } = manager;
  const hidden = state.pickerHidden;

  // for animation
  const translateY = useRef(new Animated.Value(0)).current;

  useEffect(() => {
    if (!hidden) {
      Animated.timing(translateY, {
        toValue: 0,
        duration: 250,
        useNativeDriver: true,
      }).start();
    } else {
      Animated.timing(translateY, {
        toValue: COUNTRY_PICKER_VIEW_HEIGHT,
        duration: 200,
        useNativeDriver: true,
      }).start();
    }
  }, [hidden]);

  return (
    <Animated.View
      style={{
        ...styles.pickerArea,
        transform: [{ translateY }],
      }}
    >
      <NativeToolbar
        darkMode={state.darkMode}
        onClick={() => dispatch({ type: 'setHidden', payload: true })}
        doneButtonText={doneButtonText}
        style={toolbarStyles}
      />
      <CountryPicker
        darkMode={state.darkMode}
        onChange={(c) => dispatch({ type: 'updateCountry', payload: c })}
        defaultCountry={state.countryCode}
        style={pickerStyles}
        countries={state.customCountries}
      />
    </Animated.View>
  );
};

const styles = StyleSheet.create({
  pickerArea: {
    position: 'absolute',
    zIndex: 1000,
    width: '100%',
    left: 0,
    right: 0,
    bottom: 0,
  },
  pickerView: {},
});

export default CountryPickerModal;
