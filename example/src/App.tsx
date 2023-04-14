import * as React from 'react';

import { StyleSheet, View } from 'react-native';
import {
  CountryPickerModal,
  CountryAwarePhoneInput,
  usePhoneNumberInput,
} from 'rn-phone-number-input';

export default function App() {
  const inputManager = usePhoneNumberInput({
    darkMode: true,
    defaultCountry: 'GB',
  });

  return (
    <View style={styles.container}>
      <CountryAwarePhoneInput
        manager={inputManager}
        onEndEditing={() => {
          console.log('Finished inputting number');
          // check if number is valid
          console.log('Is Valid: ' + inputManager.isValid());
          // output number in e.164 format (e.g., +12133734253)
          console.log(inputManager.getNumber());
        }}
      />
      {/* CountryPickerModal must be at root level of screen! */}
      <CountryPickerModal manager={inputManager} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    // for dark mode
    backgroundColor: '#131314',
  },
  box: {
    marginTop: 'auto',
    marginBottom: 0,
    width: '100%',
  },
});
