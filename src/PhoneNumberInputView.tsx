/* eslint-disable react-native/no-inline-styles */

import React from 'react';
import {
  StyleSheet,
  TextInput,
  TextStyle,
  View,
  ViewStyle,
} from 'react-native';
import type { InputManager } from './usePhoneNumberInput';
import CountryPickerField from './CountryPickerField';

interface PhoneNumberInputProps {
  manager: InputManager;
  containerStyles?: ViewStyle;
  fieldStyles?: ViewStyle;
  textStyles?: TextStyle;
  onEndEditing?: Function;
}

const PhoneNumberInputView = ({
  manager,
  containerStyles,
  fieldStyles,
  textStyles,
  onEndEditing,
}: PhoneNumberInputProps) => {
  const { state, dispatch } = manager;
  const backgroundColor = manager.state.darkMode ? '#1E1E20' : '#E9ECEF';
  const textColor = manager.state.darkMode ? '#BABABA' : '#000000';

  return (
    <View style={{ ...styles.container, ...containerStyles }}>
      <CountryPickerField
        manager={manager}
        fieldStyles={{ ...styles.fieldStyle, backgroundColor, ...fieldStyles }}
        textStyles={{ color: textColor, ...textStyles }}
      />
      <TextInput
        autoComplete={'tel'}
        onFocus={() => dispatch({ type: 'setHidden', payload: true })}
        onChange={(e) =>
          dispatch({
            type: 'processInput',
            payload: e.nativeEvent.text,
          })
        }
        value={state.formattedText}
        style={{
          ...styles.fieldStyle,
          backgroundColor,
          color: textColor,
          ...fieldStyles,
          flex: 1,
        }}
        keyboardType={'phone-pad'}
        keyboardAppearance={state.darkMode ? 'dark' : 'light'}
        onEndEditing={() => onEndEditing && onEndEditing()}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flexDirection: 'row',
    gap: 6,
    marginLeft: 16,
    marginRight: 16,
  },
  fieldStyle: {
    paddingTop: 24,
    paddingBottom: 24,
    paddingLeft: 16,
    paddingRight: 16,
    borderRadius: 4,
    flexDirection: 'row',
    alignItems: 'center',
    gap: 8,
    fontSize: 20,
    color: '#000000',
  },
});

export default PhoneNumberInputView;
