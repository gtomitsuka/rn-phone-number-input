#import <React/RCTViewManager.h>
#import <React/RCTUIManager.h>
#import <React/RCTFont.h>
#import "RCTBridge.h"
#import "PhoneNumberPicker.h"

@interface PhoneNumberInputViewManager : RCTViewManager
@end

@implementation PhoneNumberInputViewManager

RCT_EXPORT_MODULE(PhoneNumberInputView)

- (UIView *)view
{
    return [PhoneNumberPicker new];
}

RCT_EXPORT_VIEW_PROPERTY(items, NSArray<NSDictionary *>)
RCT_EXPORT_VIEW_PROPERTY(selectedIndex, NSInteger)
RCT_EXPORT_VIEW_PROPERTY(onChange, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(darkMode, BOOL)

RCT_CUSTOM_VIEW_PROPERTY(fontWeight, NSString, __unused PhoneNumberPicker)
{
  view.font = [RCTFont updateFont:view.font withWeight:json]; // defaults to normal
}
RCT_CUSTOM_VIEW_PROPERTY(fontStyle, NSString, __unused PhoneNumberPicker)
{
  view.font = [RCTFont updateFont:view.font withStyle:json]; // defaults to normal
}
RCT_CUSTOM_VIEW_PROPERTY(fontFamily, NSString, PhoneNumberPicker)
{
  view.font = [RCTFont updateFont:view.font withFamily:json ?: defaultView.font.familyName];
}
@end
