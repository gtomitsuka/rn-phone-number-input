#ifdef RCT_NEW_ARCH_ENABLED
#import "PhoneNumberToolbarView.h"

#import <react/renderer/components/RNPhoneNumberInputViewSpec/ComponentDescriptors.h>
#import <react/renderer/components/RNPhoneNumberInputViewSpec/EventEmitters.h>
#import <react/renderer/components/RNPhoneNumberInputViewSpec/Props.h>
#import <react/renderer/components/RNPhoneNumberInputViewSpec/RCTComponentViewHelpers.h>
#import <React/RCTConversions.h>
#import <React/RCTFont.h>

#import "RCTFabricComponentsPlugins.h"
#import "PhoneNumberToolbar.h"

using namespace facebook::react;

@interface PhoneNumberToolbarView () <RCTPhoneNumberToolbarViewViewProtocol, RCTDoneButtonDelegate>

@end

@implementation PhoneNumberToolbarView {
    PhoneNumberToolbar * _view;
}

+ (ComponentDescriptorProvider)componentDescriptorProvider
{
    return concreteComponentDescriptorProvider<PhoneNumberToolbarViewComponentDescriptor>();
}

- (instancetype)initWithFrame:(CGRect)frame
{
  if (self = [super initWithFrame:frame]) {
      static const auto defaultProps = std::make_shared<const PhoneNumberToolbarViewProps>();
      _props = defaultProps;

      _view = [[PhoneNumberToolbar alloc] initWithFrame:self.bounds];;
      _view.buttonDelegate = self;

      self.contentView = _view;
  }

  return self;
}

- (void)updateProps:(Props::Shared const &)props oldProps:(Props::Shared const &)oldProps
{
    const auto &oldViewProps = *std::static_pointer_cast<PhoneNumberToolbarViewProps const>(_props);
    const auto &newViewProps = *std::static_pointer_cast<PhoneNumberToolbarViewProps const>(props);
    
    
    if (oldViewProps.doneButtonText != newViewProps.doneButtonText) {
        [_view setDoneButtonText: RCTNSStringFromStringNilIfEmpty(newViewProps.doneButtonText)];
    }
    if (oldViewProps.darkMode != newViewProps.darkMode) {
        [_view setDarkMode: newViewProps.darkMode];
    }

    [super updateProps:props oldProps:oldProps];
}

- (void)didClick {
    if (_eventEmitter != nullptr) {
        std::dynamic_pointer_cast<const facebook::react::PhoneNumberToolbarViewEventEmitter>(_eventEmitter)
            ->onClick(facebook::react::PhoneNumberToolbarViewEventEmitter::OnClick{
            });
    }
}

Class<RCTComponentViewProtocol> PhoneNumberToolbarViewCls(void)
{
    return PhoneNumberToolbarView.class;
}

@end
#endif
