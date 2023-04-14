import codegenNativeComponent from 'react-native/Libraries/Utilities/codegenNativeComponent';
import type { HostComponent, ViewProps } from 'react-native';
import type {
  BubblingEventHandler,
  Int32,
} from 'react-native/Libraries/Types/CodegenTypes';

interface Country {
  name: string;
  emoji: string;
  tel: string;
  code: string;
}

type OnChangeEvent = Readonly<{ newValue: string; newCode: string }>;

interface NativeInputProps extends ViewProps {
  items?: Country[];
  onChange?: BubblingEventHandler<OnChangeEvent, 'onChange'>;
  darkMode?: boolean;
  selectedIndex?: Int32;
}

export default codegenNativeComponent<NativeInputProps>(
  'PhoneNumberInputView'
) as HostComponent<NativeInputProps>;
