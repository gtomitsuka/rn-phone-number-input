import { Reducer, Dispatch, useReducer } from 'react';
import type { Country } from './types';
import { countries } from './countries';
import { Keyboard } from 'react-native';
import {
  AsYouType,
  CountryCode,
  parsePhoneNumber,
  PhoneNumber,
  isValidPhoneNumber,
} from 'libphonenumber-js/max';

interface PhoneNumberInputOptions {
  // specify a default country using its ISO-3601 code (e.g., "GB")
  defaultCountry?: string;
  darkMode?: boolean;
  // optional! we provide a default set of countries
  customCountries?: Country[];
}

interface InputState {
  countryTel: string;
  countryCode: string;
  // not carried as part of state unless custom countries set
  customCountries?: Country[];
  darkMode: boolean;
  number: string;
  pickerHidden: boolean;
  inputText: string;
  formattedText: string;
}

interface InputAction {
  type: 'setHidden' | 'updateCountry' | 'processInput';
  payload: any;
}

export interface InputManager {
  dispatch: Dispatch<InputAction>;
  state: InputState;
  getNumber: () => string;
  getCountry: () => string;
  getCallingCode: () => string;
  getNumberInfo: () => PhoneNumber | undefined;
  isValid: () => boolean;
}

/* The `usePhoneNumberInput` reducer is required for easy communication
 * between the different required & optional components of PhoneNumberInput.
 * */
const usePhoneNumberInput = (
  _options?: PhoneNumberInputOptions
): InputManager => {
  const options = _options || {};

  const getCountryTel = (code: string): string => {
    const _sourceCountries = _options?.customCountries
      ? _options.customCountries
      : countries;

    const tel = _sourceCountries.find((value) => value.code === code)?.tel;

    if (!tel) throw new Error('Invalid country ' + code);
    return tel;
  };

  const [managerState, managerDispatch] = useReducer<
    Reducer<InputState, InputAction>
  >(
    (state, action) => {
      const { type, payload } = action;

      switch (type) {
        case 'setHidden':
          if (payload === false) {
            Keyboard.dismiss();
          }
          return {
            ...state,
            pickerHidden: payload,
          };
        case 'updateCountry':
          const updateTextHandler = new AsYouType(payload.code as CountryCode);
          const updatedText = updateTextHandler.input(state.inputText);

          return {
            ...state,
            countryTel: payload.tel,
            countryCode: payload.code,
            formattedText: updatedText,
            number: updateTextHandler.getNumberValue() as string,
          };
        case 'processInput':
          const inputTextHandler = new AsYouType(
            state.countryCode as CountryCode
          );
          const formattedText = inputTextHandler.input(payload);

          // handles auto-complete input
          if (inputTextHandler.isInternational()) {
            const countryCode = inputTextHandler.getCountry() as string;
            const countryTel = inputTextHandler.getCallingCode();
            return {
              ...state,
              countryTel,
              countryCode,
              formattedText:
                inputTextHandler.getNumber()?.formatNational() || '',
              number: inputTextHandler.getNumberValue() as string,
            };
          }

          return {
            ...state,
            inputText: payload,
            formattedText: formattedText,
            number: inputTextHandler.getNumberValue() as string,
          };
        default:
          throw new Error(
            `PhoneNumberInput: Invalid action "${type}" with payload "${payload}"`
          );
      }
    },
    {
      number: '',
      countryTel: options.defaultCountry
        ? getCountryTel(options.defaultCountry)
        : '+1',
      countryCode: options.defaultCountry || 'US',
      pickerHidden: true,
      darkMode: options.darkMode || false,
      customCountries: options.customCountries,
      inputText: '',
      formattedText: '',
    }
  );

  return {
    getNumber: () => managerState.number,
    getCountry: () => managerState.countryCode,
    getCallingCode: () => managerState.countryTel,
    getNumberInfo: () =>
      parsePhoneNumber(
        managerState.inputText,
        managerState.countryCode as CountryCode
      ),
    isValid: () =>
      isValidPhoneNumber(
        managerState.inputText,
        managerState.countryCode as CountryCode
      ),
    state: managerState,
    dispatch: managerDispatch,
  };
};

export default usePhoneNumberInput;
