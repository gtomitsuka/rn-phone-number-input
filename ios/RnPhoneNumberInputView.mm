#ifdef RCT_NEW_ARCH_ENABLED
#import "RnPhoneNumberInputView.h"

#import <react/renderer/components/RNRnPhoneNumberInputViewSpec/ComponentDescriptors.h>
#import <react/renderer/components/RNRnPhoneNumberInputViewSpec/EventEmitters.h>
#import <react/renderer/components/RNRnPhoneNumberInputViewSpec/Props.h>
#import <react/renderer/components/RNRnPhoneNumberInputViewSpec/RCTComponentViewHelpers.h>

#import "RCTFabricComponentsPlugins.h"
#import "Utils.h"

using namespace facebook::react;

@interface RnPhoneNumberInputView () <RCTRnPhoneNumberInputViewViewProtocol>

@end

@implementation RnPhoneNumberInputView {
    UIView * _view;
}

+ (ComponentDescriptorProvider)componentDescriptorProvider
{
    return concreteComponentDescriptorProvider<RnPhoneNumberInputViewComponentDescriptor>();
}

- (instancetype)initWithFrame:(CGRect)frame
{
  if (self = [super initWithFrame:frame]) {
    static const auto defaultProps = std::make_shared<const RnPhoneNumberInputViewProps>();
    _props = defaultProps;

    _view = [[UIView alloc] init];

    self.contentView = _view;
  }

  return self;
}

- (void)updateProps:(Props::Shared const &)props oldProps:(Props::Shared const &)oldProps
{
    const auto &oldViewProps = *std::static_pointer_cast<RnPhoneNumberInputViewProps const>(_props);
    const auto &newViewProps = *std::static_pointer_cast<RnPhoneNumberInputViewProps const>(props);

    if (oldViewProps.color != newViewProps.color) {
        NSString * colorToConvert = [[NSString alloc] initWithUTF8String: newViewProps.color.c_str()];
        [_view setBackgroundColor: [Utils hexStringToColor:colorToConvert]];
    }

    [super updateProps:props oldProps:oldProps];
}

Class<RCTComponentViewProtocol> RnPhoneNumberInputViewCls(void)
{
    return RnPhoneNumberInputView.class;
}

@end
#endif
