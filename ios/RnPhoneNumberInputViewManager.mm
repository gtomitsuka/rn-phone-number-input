#import <React/RCTViewManager.h>
#import <React/RCTUIManager.h>
#import "RCTBridge.h"
#import "Utils.h"

@interface RnPhoneNumberInputViewManager : RCTViewManager
@end

@implementation RnPhoneNumberInputViewManager

RCT_EXPORT_MODULE(RnPhoneNumberInputView)

- (UIView *)view
{
  return [[UIView alloc] init];
}

RCT_CUSTOM_VIEW_PROPERTY(color, NSString, UIView)
{
  [view setBackgroundColor: [Utils hexStringToColor:json]];
}

@end
