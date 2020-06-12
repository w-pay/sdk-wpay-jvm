#import <Foundation/Foundation.h>
#import "OAIObject.h"

/**
* Village Wallet
* APIs for Village Wallet
*
* The version of the OpenAPI document: 0.0.4
* 
*
* NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
* https://openapi-generator.tech
* Do not edit the class manually.
*/


#import "OAIBasket.h"
@protocol OAIBasket;
@class OAIBasket;



@protocol OAICustomerPaymentDetailAllOf
@end

@interface OAICustomerPaymentDetailAllOf : OAIObject

/* The ID of the merchant associated with this transaction 
 */
@property(nonatomic) NSString* merchantId;

@property(nonatomic) OAIBasket* basket;

@end
