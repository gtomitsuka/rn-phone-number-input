//
//  PhoneNumberPicker.h
//  phone-number-input
//
//  Created by Gabriel Tomitsuka on 15.03.23.
//

#import <UIKit/UIKit.h>
#import <React/UIView+React.h>

#ifdef RCT_NEW_ARCH_ENABLED
#import "PhoneNumberInputView.h"
#endif

NS_ASSUME_NONNULL_BEGIN

@interface PhoneNumberPicker : UIPickerView <UIPickerViewDataSource, UIPickerViewDelegate>

@property (nonatomic, copy) NSArray<NSDictionary *> *items;
@property (nonatomic, assign) NSInteger selectedIndex;

@property (nonatomic) bool darkMode;
@property (nonatomic, strong) UIFont *font;

@property (nonatomic, copy) RCTBubblingEventBlock onChange;

#ifdef RCT_NEW_ARCH_ENABLED
- (void)pickerView:(__unused UIPickerView *)pickerView
      didSelectRow:(NSInteger)row inComponent:(__unused NSInteger)component
  withEventEmitter:(facebook::react::SharedViewEventEmitter)eventEmitter;
#endif

@end

NS_ASSUME_NONNULL_END
