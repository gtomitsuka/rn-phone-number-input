import React from 'react';
import NativePhoneNumberToolbarView from './PhoneNumberToolbarViewNativeComponent';
import type { ViewStyle } from 'react-native';
import { StyleSheet } from 'react-native';
import { COUNTRY_PICKER_TOOLBAR_HEIGHT } from './consts';

type ToolbarProps = {
  darkMode?: boolean;
  doneButtonText?: string;
  style?: ViewStyle;
  onClick?: () => void;
};

const NativeToolbar = (props: ToolbarProps) => {
  return (
    <NativePhoneNumberToolbarView
      darkMode={props.darkMode ? props.darkMode : false}
      doneButtonText={props.doneButtonText ? props.doneButtonText : 'Done'}
      style={{ ...styles.toolbar }}
      onClick={
        props.onClick
          ? props.onClick
          : () => {
              console.log('done button clicked');
            }
      }
    />
  );
};

const styles = StyleSheet.create({
  toolbar: {
    width: '100%',
    height: COUNTRY_PICKER_TOOLBAR_HEIGHT,
  },
});

export default NativeToolbar;
