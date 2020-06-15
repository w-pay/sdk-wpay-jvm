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





@protocol OAIMerchantSchemaDetailsResultData
@end

@interface OAIMerchantSchemaDetailsResultData : OAIObject

/* The type of the schema e.g. pos, mewrchant [optional]
 */
@property(nonatomic) NSString* type;
/* A description for the schema [optional]
 */
@property(nonatomic) NSString* _description;
/* The schema content formatted according to JSON Schema standards 
 */
@property(nonatomic) NSDictionary<NSString*, NSObject*>* schema;

@end