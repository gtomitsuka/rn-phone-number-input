//
//  PhoneNumberToolbar.m
//  phone-number-input
//
//  Created by Gabriel Tomitsuka on 16.03.23.
//

#import <React/RCTConvert.h>
#import <React/RCTUtils.h>

#ifdef RCT_NEW_ARCH_ENABLED
#import <react/renderer/components/RNPhoneNumberInputViewSpec/EventEmitters.h>
#endif

#import "PhoneNumberToolbar.h"

@implementation PhoneNumberToolbar

- (instancetype)initWithFrame:(CGRect)frame
{
  if ((self = [super initWithFrame:frame])) {
      _font = [UIFont systemFontOfSize:21];
      _darkMode = false;
      _doneButtonText = @"Done";
      self.barTintColor = [UIColor colorWithRed:239/255.0 green:239/255.0 blue:244/255.0 alpha:1];
      self.translucent = YES;
      
      // Create a flexible space to push the Done button to the right
      UIBarButtonItem *flexibleSpace = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemFlexibleSpace target:nil action:nil];

      // Create a blue Done button
      _doneButton = [[UIBarButtonItem alloc] initWithTitle:_doneButtonText style:UIBarButtonItemStyleDone target:self action:@selector(doneButtonTapped:)];
      _doneButton.tintColor = [UIColor colorWithRed:0/255.0 green:122/255.0 blue:255/255.0 alpha:1];
      
      [self setItems:@[flexibleSpace, _doneButton]];
  }
  return self;
}

- (void)setDarkMode:(bool)darkMode {
    if (darkMode == true) {
        self.barTintColor = [UIColor colorWithRed:58/255.0 green:58/255.0 blue:60/255.0 alpha:1];
    } else {
        self.barTintColor = [UIColor colorWithRed:239/255.0 green:239/255.0 blue:244/255.0 alpha:1];
    }
}

- (void)setDoneButtonText:(NSString *)doneButtonText {
    [_doneButton setTitle:doneButtonText];
}

#ifdef RCT_NEW_ARCH_ENABLED
- (void)doneButtonTapped:(UIBarButtonItem *)sender {
    if ([self.buttonDelegate respondsToSelector:@selector(didClick)]) {
        [self.buttonDelegate didClick];
    }
}
#else
- (void)doneButtonTapped:(UIBarButtonItem *)sender {
    if (_onClick) {
        _onClick(@{});
    }
}
#endif


@end
