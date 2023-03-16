#import <React/RCTViewManager.h>
#import <React/RCTUIManager.h>
#import <React/RCTFont.h>
#import "RCTBridge.h"
#import "PhoneNumberToolbar.h"

@interface PhoneNumberToolbarViewManager : RCTViewManager
@end

@implementation PhoneNumberToolbarViewManager

RCT_EXPORT_MODULE(PhoneNumberToolbarView)

- (UIView *)view
{
    return [PhoneNumberToolbar new];
}

RCT_EXPORT_VIEW_PROPERTY(doneButtonText, NSString)
RCT_EXPORT_VIEW_PROPERTY(onClick, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(darkMode, BOOL)

RCT_CUSTOM_VIEW_PROPERTY(fontWeight, NSString, __unused PhoneNumberToolbar)
{
  view.font = [RCTFont updateFont:view.font withWeight:json]; // defaults to normal
}
RCT_CUSTOM_VIEW_PROPERTY(fontStyle, NSString, __unused PhoneNumberToolbar)
{
  view.font = [RCTFont updateFont:view.font withStyle:json]; // defaults to normal
}
RCT_CUSTOM_VIEW_PROPERTY(fontFamily, NSString, PhoneNumberToolbar)
{
  view.font = [RCTFont updateFont:view.font withFamily:json ?: defaultView.font.familyName];
}
@end
