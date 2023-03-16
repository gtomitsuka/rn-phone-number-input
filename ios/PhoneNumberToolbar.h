//
//  PhoneNumberToolbar.h
//  phone-number-input
//
//  Created by Gabriel Tomitsuka on 16.03.23.
//

#import <UIKit/UIKit.h>
#import <React/UIView+React.h>

#ifdef RCT_NEW_ARCH_ENABLED
#import "PhoneNumberToolbarView.h"
#endif

NS_ASSUME_NONNULL_BEGIN

@protocol RCTDoneButtonDelegate <NSObject>

- (void)didClick;

@end

@interface PhoneNumberToolbar : UIToolbar

@property (nonatomic, strong) NSString *doneButtonText;
@property (nonatomic) bool darkMode;
@property (nonatomic, strong) UIFont *font;
@property (nonatomic, strong) UIBarButtonItem *doneButton;

@property (nonatomic, copy) RCTBubblingEventBlock onClick;

#ifdef RCT_NEW_ARCH_ENABLED
@property (nonatomic, weak) id<RCTDoneButtonDelegate> buttonDelegate;
#endif

@end

NS_ASSUME_NONNULL_END
