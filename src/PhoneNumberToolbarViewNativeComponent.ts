import codegenNativeComponent from 'react-native/Libraries/Utilities/codegenNativeComponent';
import type { HostComponent, ViewProps } from 'react-native';
import type { BubblingEventHandler } from 'react-native/Libraries/Types/CodegenTypes';

type OnChangeEvent = Readonly<{}>;

interface NativeToolbarProps extends ViewProps {
  onClick?: BubblingEventHandler<OnChangeEvent, 'onSubmit'>;
  doneButtonText?: string;
  darkMode?: boolean;
}
export default codegenNativeComponent<NativeToolbarProps>(
  'PhoneNumberToolbarView'
) as HostComponent<NativeToolbarProps>;
