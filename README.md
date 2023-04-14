# ☎️ rn-phone-number-input
![platforms](https://img.shields.io/badge/platforms-iOS-brightgreen.svg?style=flat-square&colorB=191A17)

<div align="center">
<b>A beautiful yet customizable phone number input component for React Native that feels native.</b>
<br/>
<a href="#Features">Features</a> • <a href="#Installation">Installation</a> • <a href="#Getting Started">Getting Started</a> • <a href="#Reference">Reference</a> • <a href="https://github.com/gtomitsuka/rn-phone-number-input/tree/main/example">Examples</a>
<br /><br>
<video width="348" height="671" loop video autoplay>
    <source src="https://i.imgur.com/26jP9vX.mp4" type="video/mp4">
    Your browser does not support the video tag.
</video>
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

```jsx
import PhoneNumberInput from 'rn-phone-number-input';

export const InputArea = () => {
  // input in e.164 format
  const [input, setInput] = setState('');

  return <PhoneNumberInput
            value={input}
            setValue={setInput}
            defaultCountryCode="+1"
          />
}
```

## Examples
Examples can be found in the [examples](https://github.com/gtomitsuka/rn-phone-number-input/tree/main/example) folder.

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

## Reference

🚧 Coming Soon – in the meantime, refer to the TypeScript types & interfaces.

Keep in mind this library doesn't support web and doesn't have any current plans to do so.
