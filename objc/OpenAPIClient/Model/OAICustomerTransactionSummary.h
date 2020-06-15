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


#import "OAICommonTransactionSummary.h"
#import "OAICustomerTransactionSummaryAllOf.h"
#import "OAICustomerTransactionSummaryAllOfInstruments.h"
@protocol OAICommonTransactionSummary;
@class OAICommonTransactionSummary;
@protocol OAICustomerTransactionSummaryAllOf;
@class OAICustomerTransactionSummaryAllOf;
@protocol OAICustomerTransactionSummaryAllOfInstruments;
@class OAICustomerTransactionSummaryAllOfInstruments;



@protocol OAICustomerTransactionSummary
@end

@interface OAICustomerTransactionSummary : OAICommonTransactionSummary

/* The ID of the merchant associated with this transaction 
 */
@property(nonatomic) NSString* merchantId;
/* The instruments used to make the payment.  For refunds and cash back amounts will be negative 
 */
@property(nonatomic) NSArray<OAICustomerTransactionSummaryAllOfInstruments>* instruments;

@end