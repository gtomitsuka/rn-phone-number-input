import * as React from 'react';

import { StyleSheet, View } from 'react-native';
import {
  CountryPickerModal,
  CountryAwarePhoneInput,
  usePhoneNumberInput,
} from 'rn-phone-number-input';

// not necessary! just a showcase for i18n capability
import i18nCountries from 'i18n-iso-countries';
if (i18nCountries)
  i18nCountries.registerLocale(require('i18n-iso-countries/langs/de.json'));

export default function App() {
  const inputManager = usePhoneNumberInput({
    darkMode: true,
    defaultCountry: 'GB',
    // localize: (code) => i18nCountries.getName(code, 'de'),
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
