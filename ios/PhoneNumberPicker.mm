//
//  PhoneNumberPicker.m
//  phone-number-input
//
//  Created by Gabriel Tomitsuka on 15.03.23.
//

#import "PhoneNumberPicker.h"
#import <React/RCTConvert.h>
#import <React/RCTUtils.h>

#ifdef RCT_NEW_ARCH_ENABLED
#import <react/renderer/components/RNPhoneNumberInputViewSpec/EventEmitters.h>
#import <React/RCTConversions.h>
#endif

@implementation PhoneNumberPicker

- (instancetype)initWithFrame:(CGRect)frame
{
  if ((self = [super initWithFrame:frame])) {
      _font = [UIFont systemFontOfSize:21];
      _selectedIndex = NSNotFound;
      _darkMode = false;

#ifdef RCT_NEW_ARCH_ENABLED
      // handled in PhoneNumberInputView
#else
      self.delegate = self;
#endif
      self.dataSource = self;
  }
  return self;
}

- (void)setItems:(NSArray<NSDictionary *> *)items
{
  _items = [items copy];
  [self setNeedsLayout];
}

- (void)setSelectedIndex:(NSInteger)selectedIndex
{
  if (_selectedIndex != selectedIndex) {
    BOOL animated = _selectedIndex != NSNotFound; // Don't animate the initial value
    _selectedIndex = selectedIndex;
    dispatch_async(dispatch_get_main_queue(), ^{
      [self selectRow:selectedIndex inComponent:0 animated:animated];
    });
  }
}

- (void) setFont:(UIFont *)font
{
  _font = font;
  [self reloadAllComponents];
  [self setNeedsLayout];
}

- (void) setDarkMode:(bool)darkMode {
    _darkMode = darkMode;
    [self reloadAllComponents];
    [self setNeedsLayout];
}

#pragma mark - UIPickerView Data Source
- (NSInteger)numberOfComponentsInPickerView:(UIPickerView *)pickerView {
    return 1;
}

- (NSInteger)pickerView:(UIPickerView *)pickerView numberOfRowsInComponent:(NSInteger)component {
    return _items.count;
}

#pragma mark - UIPickerView Delegate

- (UIView *)pickerView:(UIPickerView *)pickerView viewForRow:(NSInteger)row forComponent:(NSInteger)component reusingView:(UIView *)view {
    UIView *customView = view;
    UILabel *leftLabel;
    UILabel *rightLabel;
    CGFloat padding = 40;

    if (customView) {
        leftLabel = customView.subviews[0];
        rightLabel = customView.subviews[1];
    } else {
        customView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, pickerView.frame.size.width, 40)];

        // Create and configure the left label
        leftLabel = [[UILabel alloc] initWithFrame:CGRectMake(padding, 0, pickerView.frame.size.width * 0.66 - padding, 40)];
        leftLabel.font = [UIFont systemFontOfSize:18];
        leftLabel.textAlignment = NSTextAlignmentLeft;
        [customView addSubview:leftLabel];

        // Create and configure the right label
        rightLabel = [[UILabel alloc] initWithFrame:CGRectMake(pickerView.frame.size.width * 0.66, 0, pickerView.frame.size.width * 0.34 - padding, 40)];
        rightLabel.font = [UIFont systemFontOfSize:18];
        rightLabel.textAlignment = NSTextAlignmentRight;
        [customView addSubview:rightLabel];
    }

    // Set the label texts
    NSDictionary *dict = _items[row];
    leftLabel.text = [NSString stringWithFormat:@"%@ %@", dict[@"emoji"], dict[@"name"]];
    rightLabel.text = dict[@"tel"];

    // Set the dark color mode
    if (_darkMode) {
        leftLabel.textColor = [UIColor whiteColor];
        rightLabel.textColor = [UIColor whiteColor];
        pickerView.backgroundColor = [UIColor colorWithRed:58/255.0 green:58/255.0 blue:60/255.0 alpha:1];
    } else {
        pickerView.backgroundColor = [UIColor colorWithRed:210/255.0 green:213/255.0 blue:219/255.0 alpha:1];
    }

    return customView;
}

- (void)pickerView:(__unused UIPickerView *)pickerView
      didSelectRow:(NSInteger)row inComponent:(__unused NSInteger)component
{
  _selectedIndex = row;
  if (_onChange && _items.count > (NSUInteger)row) {
    _onChange(@{
      @"newValue": RCTNullIfNil(_items[row][@"tel"]),
      @"newCode": RCTNullIfNil(_items[row][@"code"]),
    });
  }
}

#ifdef RCT_NEW_ARCH_ENABLED
- (void)pickerView:(__unused UIPickerView *)pickerView
      didSelectRow:(NSInteger)row inComponent:(__unused NSInteger)component
  withEventEmitter:(facebook::react::SharedViewEventEmitter)eventEmitter
{
    _selectedIndex = row;
    if (eventEmitter != nullptr && _items.count > (NSUInteger)row) {
        std::dynamic_pointer_cast<const facebook::react::PhoneNumberInputViewEventEmitter>(eventEmitter)
            ->onChange(facebook::react::PhoneNumberInputViewEventEmitter::OnChange{
                .newValue = RCTStringFromNSString(RCTNullIfNil(_items[row][@"tel"])),
                .newCode = RCTStringFromNSString(RCTNullIfNil(_items[row][@"code"])),
            });
    }
}
#endif

@end
