# ☎️ rn-phone-number-input
[![npm version](https://img.shields.io/npm/v/rn-phone-number-input.svg)](https://www.npmjs.com/package/rn-phone-number-input)
[![Build](https://github.com/gtomitsuka/rn-phone-number-input/workflows/CI/badge.svg)](https://github.com//gtomitsuka/rn-phone-number-input/actions) 
![platforms](https://img.shields.io/badge/platforms-iOS-brightgreen.svg?style=flat-square&colorB=191A17)
![MIT License](https://img.shields.io/npm/l/@react-native-picker/picker.svg)

<div align="center">
<b>A beautiful yet customizable phone number input component for React Native that feels native.</b>
<br/>
<a href="#features">Features</a> • <a href="#installation">Installation</a> • <a href="#gettingstarted">Getting Started</a> • <a href="#advancedusage">Advanced Usage</a> • <a href="#reference">Reference</a>
<br/><br/>
<video src="https://user-images.githubusercontent.com/10295671/232161870-b5033732-e722-4575-868d-aa8810d4d66f.mp4" width=250 loop autoplay />
</div>


**⚠️ Warning**: This library doesn't support Android yet.

## Features
* International phone number input (E.164-compatible)
* Phone number masks
* Searchable country list
* Built-in number verification
* Handles auto-complete
* Supports Fabric (React Native 0.71+)
* Light & dark modes
* Customizable input component
* Written in TypeScript

## Installation
```
npm install rn-phone-number-input
```
or
```
yarn add rn-phone-number-input
```
And run `cd ios && pod install`

## Getting Started
In order to support a wide range of apps, this library relies on multiple components & hooks:
* `<CountryPickerModal />` **(required)**: Native picker modal that presents countries and handles user selection.
* `usePhoneNumberInput(options)` **(required)**: Hook for managing state,
formatting/validating numbers, bridging components & more
* `<CountryAwarePhoneInput />`: UI element that toggles country picker modal & handles phone
number text input. If you'd like to use a custom UI element, refer to [Custom Components](#Custom Components).

```tsx
import {
  CountryAwarePhoneInput,
  usePhoneNumberInput,
  CountryPickerModal
} from 'rn-phone-number-input';

export default function App() {
  const inputManager = usePhoneNumberInput({
    darkMode: true,
    defaultCountry: 'GB',
  });

  return (
    <View>
      <Text>Phone number:</Text>

      <CountryAwarePhoneInput
        manager={inputManager}
        onEndEditing={() => {
          console.log('Finished inputting number');
          // check if number is valid
          console.log('Is valid: ' + inputManager.isValid());
          // output number in e.164 format (e.g., +12133734253)
          console.log(inputManager.getNumber);
        }}
      />
      {/* CountryPickerModal must be at root level of screen! */}
      <CountryPickerModal manager={inputManager} />
    </View>
  );
}
```

## Advanced Usage
### Auto-detect user's country
Lowering friction during onboarding can drastically improve sign-up rates –
this package enables you to do so through custom default countries.

To do so, you can combine it with [react-native-localize](https://github.com/zoontek/react-native-localize):

```tsx
import { usePhoneNumberInput } from 'rn-phone-number-input';
import * as RNLocalize from "react-native-localize";

const App = () => {
  const inputManager = usePhoneNumberInput({
    defaultCountry: RNLocalize.getCountry(),
  })

  // same as Getting Started
}
```

### Custom Components
If you only want to use the modal or require additional functionality, you'll need to implement dispatches for the `usePhoneNumberInput()` hook.
Internally, the hook is a reducer with a state & dispatcher.

Here's an example for basic usage:

```tsx
import { InputManager, usePhoneNumberInput } from 'rn-phone-number-input';

const ExampleComponent = (manager: InputManager) => {
  const { state, dispatch } = manager;

  // open modal
  dispatch({ type: 'setHidden', payload: false });

  // close modal
  dispatch({ type: 'setHidden', payload: true });

  // change country – requires both ISO code and tel to avoid ambiguity
  dispatch({ type: 'updateCountry', payload: { tel: '+1', code: 'US' } });

  // process user input, parses number & updates number / formatted text
  // copy-pasted / auto-completed automatically handled by this method,
  // no additional logic required
  dispatch({ type: 'processInput', payload: '7071001000' });
}

// Usage
const inputManager = usePhoneNumberInput({ ... });
<ExampleComponent manager={inputManager} />
```

## Examples
Examples can be found in the [examples](https://github.com/gtomitsuka/rn-phone-number-input/tree/main/example) folder.


## Reference
### `usePhoneNumberInput(options): InputManager`
#### Options
* `defaultCountry` (string): ISO-3601 code of country shown first by default
* `darkMode` (boolean): color scheme used. Please note that changing this may not immediately re-render native component
* `customCountries` (array): if you'd like to use a country list other than [this one](https://github.com/gtomitsuka/rn-phone-number-input/blob/main/src/countries.ts)
  * Elements must implement [Country](https://github.com/gtomitsuka/rn-phone-number-input/blob/main/src/types.ts) interface:
  `{ tel: '+1', name: 'United States', emoji: '🇺🇸', code: 'US'}`

#### InputManager
* `getNumber() -> string`: E.164-formatted international phone number (ex.: `+123456789`)
* `getCountry() -> string`: ISO-3601 country code selected by user (ex.: `US`)
* `getCallingCode() -> string`: Calling code for country selected by user (ex.: `+1`)
* `isValid() -> string`: Verifies if number is valid.
* `getNumberInfo() -> PhoneNumber`: Additional phone number information. See [PhoneNumber](https://github.com/catamphetamine/libphonenumber-js#phonenumber) for reference.
* `state`: Offers direct access to state. Mostly internal, refer to [source](https://github.com/gtomitsuka/rn-phone-number-input/blob/main/src/usePhoneNumberInput.tsx#L22)
* `dispatch({type, payload})`: Handles user input. Mostly internal, see [Advanced Usage](#Advanced Usage)

### `<CountryAwarePhoneInput />`
#### Props
* `manager` **(required)**: uses `InputManager` for logic
* `onEndEditing`: event handler for when user dismisses text input
* `containerStyles`: override container styles, refer to [this file](https://github.com/gtomitsuka/rn-phone-number-input/blob/5a85b0bdfb15e99f2768680d0b5f5259d3687e49/src/CountryAwarePhoneInput.tsx#L66) for defaults.
* `fieldStyles`: override input field styles, refer to [this file](https://github.com/gtomitsuka/rn-phone-number-input/blob/5a85b0bdfb15e99f2768680d0b5f5259d3687e49/src/CountryAwarePhoneInput.tsx#L72) for defaults.
* `textStyles`: override text styles, mostly used when special fonts / font sizes are required.

### `<CountryPickerModal />`
**Important:** Keep this at the root of the view hierarchy for the screen.

#### Props
* `manager` **(required)**: uses `InputManager` for logic
* `toolbarStyles`: custom styles for native toolbar. Don't use unless absolutely necessary (and, if you do, prefer OS-specific code with `Platform.OS`). Can easily lead to compatibility issues.
* `pickerStyles`: custom styles for native picker. Don't use unless absolutely necessary (and, if you do, prefer OS-specific code with `Platform.OS`). Can easily lead to compatibility issues.
