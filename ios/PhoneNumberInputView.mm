#ifdef RCT_NEW_ARCH_ENABLED
#import "PhoneNumberInputView.h"

#import <react/renderer/components/RNPhoneNumberInputViewSpec/ComponentDescriptors.h>
#import <react/renderer/components/RNPhoneNumberInputViewSpec/EventEmitters.h>
#import <react/renderer/components/RNPhoneNumberInputViewSpec/Props.h>
#import <react/renderer/components/RNPhoneNumberInputViewSpec/RCTComponentViewHelpers.h>
#import <React/RCTConversions.h>
#import <React/RCTFont.h>

#import "RCTFabricComponentsPlugins.h"
#import "PhoneNumberPicker.h"

using namespace facebook::react;

@interface PhoneNumberInputView () <UIPickerViewDelegate, RCTPhoneNumberInputViewViewProtocol>

@end

@implementation PhoneNumberInputView {
    PhoneNumberPicker * _view;
}

+ (ComponentDescriptorProvider)componentDescriptorProvider
{
    return concreteComponentDescriptorProvider<PhoneNumberInputViewComponentDescriptor>();
}

- (instancetype)initWithFrame:(CGRect)frame
{
  if (self = [super initWithFrame:frame]) {
      static const auto defaultProps = std::make_shared<const PhoneNumberInputViewProps>();
      _props = defaultProps;

      _view = [[PhoneNumberPicker alloc] initWithFrame:self.bounds];;
      _view.delegate = self;

      self.contentView = _view;
  }

  return self;
}

- (void)updateProps:(Props::Shared const &)props oldProps:(Props::Shared const &)oldProps
{
    const auto &oldViewProps = *std::static_pointer_cast<PhoneNumberInputViewProps const>(_props);
    const auto &newViewProps = *std::static_pointer_cast<PhoneNumberInputViewProps const>(props);

    NSMutableArray *items = [NSMutableArray new];
    for (PhoneNumberInputViewItemsStruct item : newViewProps.items)
    {
        NSMutableDictionary *dictItem = [NSMutableDictionary new];
        dictItem[@"name"] = RCTNSStringFromStringNilIfEmpty(item.name);
        dictItem[@"emoji"] = RCTNSStringFromStringNilIfEmpty(item.emoji);
        dictItem[@"tel"] = RCTNSStringFromStringNilIfEmpty(item.tel);
        [items addObject:dictItem];
    }
    
    [_view setItems: items];
    
    
    if (oldViewProps.selectedIndex != newViewProps.selectedIndex) {
        [_view setSelectedIndex: newViewProps.selectedIndex];
    }
    if (oldViewProps.darkMode != newViewProps.darkMode) {
        [_view setDarkMode: newViewProps.darkMode];
    }

    [super updateProps:props oldProps:oldProps];
}

#pragma mark - UIPickerView Delegate
- (UIView *)pickerView:(UIPickerView *)pickerView viewForRow:(NSInteger)row forComponent:(NSInteger)component reusingView:(UIView *)view {
    return [_view pickerView:pickerView viewForRow:row forComponent:component reusingView:view];
}

- (void)pickerView:(__unused UIPickerView *)pickerView
      didSelectRow:(NSInteger)row inComponent:(__unused NSInteger)component {
    return [_view pickerView:pickerView
                didSelectRow:row
                 inComponent:component
                 withEventEmitter:_eventEmitter];
}

Class<RCTComponentViewProtocol> PhoneNumberInputViewCls(void)
{
    return PhoneNumberInputView.class;
}

@end
#endif
