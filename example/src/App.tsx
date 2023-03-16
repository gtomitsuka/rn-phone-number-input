import * as React from 'react';

import { Button, StyleSheet, View } from 'react-native';
import { CountryPickerView } from 'rn-phone-number-input';
import { useState } from 'react';
import { countryTable } from '../../src/countries';

export default function App() {
  const [hidden, setHidden] = useState(false);
  const [country, setCountry] = useState('+1');

  return (
    <View style={styles.container}>
      <Button
        title={countryTable[country]?.emoji + ' ' + country}
        onPress={() => setHidden((_hidden) => !_hidden)}
      />
      <View style={styles.box}>
        <CountryPickerView
          darkMode={false}
          hidden={hidden}
          setHidden={setHidden}
          setCountry={setCountry}
        />
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'flex-end',
  },
  box: {
    marginTop: 'auto',
    marginBottom: 0,
    width: '100%',
  },
});
