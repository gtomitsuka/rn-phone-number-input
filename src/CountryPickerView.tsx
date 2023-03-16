/* eslint-disable react-hooks/exhaustive-deps */

import React, { useEffect, useRef } from 'react';
import { Animated, StyleSheet } from 'react-native';
import { CountryPickerInput, CountryPickerToolbar } from './index';
import { COUNTRY_PICKER_VIEW_HEIGHT } from './consts';

interface CountryPickerProps {
  hidden: boolean;
  setHidden: React.Dispatch<React.SetStateAction<boolean>>;
  setCountry: React.Dispatch<React.SetStateAction<string>>;
  darkMode?: boolean;
}

const CountryPickerView = ({
  hidden,
  setHidden,
  darkMode,
  setCountry,
}: CountryPickerProps) => {
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
      <CountryPickerToolbar
        darkMode={darkMode}
        onClick={() => setHidden(true)}
      />
      <CountryPickerInput darkMode={darkMode} onChange={(c) => setCountry(c)} />
    </Animated.View>
  );
};

const styles = StyleSheet.create({
  pickerArea: {
    marginTop: 'auto',
    marginBottom: 0,
    display: 'flex',
    zIndex: 1000,
  },
  pickerView: {},
});

export default CountryPickerView;
