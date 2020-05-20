# MerchantApi

All URIs are relative to *http://localhost:3000*

Method | HTTP request | Description
------------- | ------------- | -------------
[**cancelPaymentQRCode**](MerchantApi.md#cancelPaymentQRCode) | **DELETE** /merchant/qr/{qrId} | Invalidate QR Code
[**createMerchantSchema**](MerchantApi.md#createMerchantSchema) | **POST** /merchant/schema | Add Schema
[**createPaymentQRCode**](MerchantApi.md#createPaymentQRCode) | **POST** /merchant/qr | Create QR Code
[**createPaymentRequest**](MerchantApi.md#createPaymentRequest) | **POST** /merchant/payments | Create Payment
[**deleteMerchantPayment**](MerchantApi.md#deleteMerchantPayment) | **DELETE** /merchant/payments/{paymentRequestId} | Delete Payment
[**getMerchantPaymentDetails**](MerchantApi.md#getMerchantPaymentDetails) | **GET** /merchant/payments/{paymentRequestId} | Get Payment Details
[**getMerchantPayments**](MerchantApi.md#getMerchantPayments) | **GET** /merchant/payments | Get Payment List
[**getMerchantPreferences**](MerchantApi.md#getMerchantPreferences) | **GET** /merchant/preferences | Get Preferences
[**getMerchantSchemaDetails**](MerchantApi.md#getMerchantSchemaDetails) | **GET** /merchant/schema/{schemaId} | Get Schema Details
[**getMerchantSchemas**](MerchantApi.md#getMerchantSchemas) | **GET** /merchant/schema | Get Schema List
[**getMerchantTransactionDetails**](MerchantApi.md#getMerchantTransactionDetails) | **GET** /merchant/transactions/{transactionId} | Get Transaction Details
[**getMerchantTransactions**](MerchantApi.md#getMerchantTransactions) | **GET** /merchant/transactions | Get Transaction List
[**getPaymentQRCodeContent**](MerchantApi.md#getPaymentQRCodeContent) | **GET** /merchant/qr/{qrId} | Get QR Code Content
[**refundMerchantTransaction**](MerchantApi.md#refundMerchantTransaction) | **POST** /merchant/transactions/{transactionId}/refund | Refund Transaction
[**setMerchantPreferences**](MerchantApi.md#setMerchantPreferences) | **POST** /merchant/preferences | Set Preferences


<a name="cancelPaymentQRCode"></a>
# **cancelPaymentQRCode**
> cancelPaymentQRCode(qrId)

Invalidate QR Code

Cancel an existing QC code.  Effectively expires the QR code

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.client.ApiClient;
import au.com.woolworths.village.sdk.client.ApiException;
import au.com.woolworths.village.sdk.client.Configuration;
import au.com.woolworths.village.sdk.client.auth.*;
import au.com.woolworths.village.sdk.client.models.*;
import au.com.woolworths.village.sdk.api.MerchantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    MerchantApi apiInstance = new MerchantApi(defaultClient);
    String qrId = dca8edc5-bbb7-44c0-8056-a5daf4327601; // String | The ID of the specific QR Code
    try {
      apiInstance.cancelPaymentQRCode(qrId);
    } catch (ApiException e) {
      System.err.println("Exception when calling MerchantApi#cancelPaymentQRCode");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qrId** | **String**| The ID of the specific QR Code |

### Return type

null (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**204** | QR code has been successfully expired.  Not content returned |  -  |
**400** | The QR code specified doesn&#39;t exist |  -  |

<a name="createMerchantSchema"></a>
# **createMerchantSchema**
> CreateMerchantSchemaResults createMerchantSchema(merchantSchema)

Add Schema

Add a new schema that can be used in schema based payloads for this merchant

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.client.ApiClient;
import au.com.woolworths.village.sdk.client.ApiException;
import au.com.woolworths.village.sdk.client.Configuration;
import au.com.woolworths.village.sdk.client.auth.*;
import au.com.woolworths.village.sdk.client.models.*;
import au.com.woolworths.village.sdk.api.MerchantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    MerchantApi apiInstance = new MerchantApi(defaultClient);
    MerchantSchema merchantSchema = new MerchantSchema(); // MerchantSchema | 
    try {
      CreateMerchantSchemaResults result = apiInstance.createMerchantSchema(merchantSchema);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MerchantApi#createMerchantSchema");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **merchantSchema** | [**MerchantSchema**](MerchantSchema.md)|  |

### Return type

[**CreateMerchantSchemaResults**](CreateMerchantSchemaResults.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |

<a name="createPaymentQRCode"></a>
# **createPaymentQRCode**
> CreatePaymentQRCodeResults createPaymentQRCode(paymentQRCodeDetails)

Create QR Code

Create a new QR code for an existing payment

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.client.ApiClient;
import au.com.woolworths.village.sdk.client.ApiException;
import au.com.woolworths.village.sdk.client.Configuration;
import au.com.woolworths.village.sdk.client.auth.*;
import au.com.woolworths.village.sdk.client.models.*;
import au.com.woolworths.village.sdk.api.MerchantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    MerchantApi apiInstance = new MerchantApi(defaultClient);
    PaymentQRCodeDetails paymentQRCodeDetails = new PaymentQRCodeDetails(); // PaymentQRCodeDetails | 
    try {
      CreatePaymentQRCodeResults result = apiInstance.createPaymentQRCode(paymentQRCodeDetails);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MerchantApi#createPaymentQRCode");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **paymentQRCodeDetails** | [**PaymentQRCodeDetails**](PaymentQRCodeDetails.md)|  |

### Return type

[**CreatePaymentQRCodeResults**](CreatePaymentQRCodeResults.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |
**400** | The specified payment request doesn&#39;t exist or has already been closed |  -  |

<a name="createPaymentRequest"></a>
# **createPaymentRequest**
> CreatePaymentRequestResults createPaymentRequest(merchantPaymentRequest)

Create Payment

Create a new payment request that can then be presented to a customer for payment

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.client.ApiClient;
import au.com.woolworths.village.sdk.client.ApiException;
import au.com.woolworths.village.sdk.client.Configuration;
import au.com.woolworths.village.sdk.client.auth.*;
import au.com.woolworths.village.sdk.client.models.*;
import au.com.woolworths.village.sdk.api.MerchantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    MerchantApi apiInstance = new MerchantApi(defaultClient);
    MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest(); // MerchantPaymentRequest | 
    try {
      CreatePaymentRequestResults result = apiInstance.createPaymentRequest(merchantPaymentRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MerchantApi#createPaymentRequest");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **merchantPaymentRequest** | [**MerchantPaymentRequest**](MerchantPaymentRequest.md)|  |

### Return type

[**CreatePaymentRequestResults**](CreatePaymentRequestResults.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |

<a name="deleteMerchantPayment"></a>
# **deleteMerchantPayment**
> deleteMerchantPayment(paymentRequestId)

Delete Payment

Cancel an existing payment by setting the expiration date/time to now and setting the remaining uses to 0.  Will only be successful if the payment is still pending.  Completed payments need to be refunded using the dedicated API for that purpose

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.client.ApiClient;
import au.com.woolworths.village.sdk.client.ApiException;
import au.com.woolworths.village.sdk.client.Configuration;
import au.com.woolworths.village.sdk.client.auth.*;
import au.com.woolworths.village.sdk.client.models.*;
import au.com.woolworths.village.sdk.api.MerchantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    MerchantApi apiInstance = new MerchantApi(defaultClient);
    String paymentRequestId = "paymentRequestId_example"; // String | The ID of the specific payment request
    try {
      apiInstance.deleteMerchantPayment(paymentRequestId);
    } catch (ApiException e) {
      System.err.println("Exception when calling MerchantApi#deleteMerchantPayment");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **paymentRequestId** | **String**| The ID of the specific payment request |

### Return type

null (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**204** | Payment request has been successfully expired.  Not content returned |  -  |
**400** | The payment request specified doesn&#39;t exist |  -  |

<a name="getMerchantPaymentDetails"></a>
# **getMerchantPaymentDetails**
> GetMerchantPaymentDetailsResults getMerchantPaymentDetails(paymentRequestId)

Get Payment Details

Get the details for a specific payment.  Provides all types of payments and all payment content

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.client.ApiClient;
import au.com.woolworths.village.sdk.client.ApiException;
import au.com.woolworths.village.sdk.client.Configuration;
import au.com.woolworths.village.sdk.client.auth.*;
import au.com.woolworths.village.sdk.client.models.*;
import au.com.woolworths.village.sdk.api.MerchantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    MerchantApi apiInstance = new MerchantApi(defaultClient);
    String paymentRequestId = "paymentRequestId_example"; // String | The ID of the specific payment request
    try {
      GetMerchantPaymentDetailsResults result = apiInstance.getMerchantPaymentDetails(paymentRequestId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MerchantApi#getMerchantPaymentDetails");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **paymentRequestId** | **String**| The ID of the specific payment request |

### Return type

[**GetMerchantPaymentDetailsResults**](GetMerchantPaymentDetailsResults.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |
**400** | The specified Payment Request ID doesn&#39;t exist |  -  |

<a name="getMerchantPayments"></a>
# **getMerchantPayments**
> GetMerchantPaymentsResults getMerchantPayments(type, pageSize, page)

Get Payment List

Get a list of the payments initiated by the merchant, both pending and complete

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.client.ApiClient;
import au.com.woolworths.village.sdk.client.ApiException;
import au.com.woolworths.village.sdk.client.Configuration;
import au.com.woolworths.village.sdk.client.auth.*;
import au.com.woolworths.village.sdk.client.models.*;
import au.com.woolworths.village.sdk.api.MerchantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    MerchantApi apiInstance = new MerchantApi(defaultClient);
    String type = ACTIVE; // String | The type of payment requests to return: active, inactive or both
    Integer pageSize = 25; // Integer | The number of records to return for this page.  Defaults to 25 if absent
    Integer page = 1; // Integer | The page of results to return with 1 indicating the first page.  Defaults to 1 if absent
    try {
      GetMerchantPaymentsResults result = apiInstance.getMerchantPayments(type, pageSize, page);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MerchantApi#getMerchantPayments");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **type** | **String**| The type of payment requests to return: active, inactive or both | [optional] [default to ACTIVE] [enum: ACTIVE, INACTIVE, ALL]
 **pageSize** | **Integer**| The number of records to return for this page.  Defaults to 25 if absent | [optional] [default to 25]
 **page** | **Integer**| The page of results to return with 1 indicating the first page.  Defaults to 1 if absent | [optional] [default to 1]

### Return type

[**GetMerchantPaymentsResults**](GetMerchantPaymentsResults.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |

<a name="getMerchantPreferences"></a>
# **getMerchantPreferences**
> CustomerPreferencesResult getMerchantPreferences()

Get Preferences

Get the preferences previously set by the customer or merchant (depending on calling identity)

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.client.ApiClient;
import au.com.woolworths.village.sdk.client.ApiException;
import au.com.woolworths.village.sdk.client.Configuration;
import au.com.woolworths.village.sdk.client.auth.*;
import au.com.woolworths.village.sdk.client.models.*;
import au.com.woolworths.village.sdk.api.MerchantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    MerchantApi apiInstance = new MerchantApi(defaultClient);
    try {
      CustomerPreferencesResult result = apiInstance.getMerchantPreferences();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MerchantApi#getMerchantPreferences");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**CustomerPreferencesResult**](CustomerPreferencesResult.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |

<a name="getMerchantSchemaDetails"></a>
# **getMerchantSchemaDetails**
> MerchantSchemaDetailsResult getMerchantSchemaDetails(schemaId)

Get Schema Details

Get the list of currently usable schema previously added for the merchant

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.client.ApiClient;
import au.com.woolworths.village.sdk.client.ApiException;
import au.com.woolworths.village.sdk.client.Configuration;
import au.com.woolworths.village.sdk.client.auth.*;
import au.com.woolworths.village.sdk.client.models.*;
import au.com.woolworths.village.sdk.api.MerchantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    MerchantApi apiInstance = new MerchantApi(defaultClient);
    String schemaId = "schemaId_example"; // String | The ID of the specific schema to get details for
    try {
      MerchantSchemaDetailsResult result = apiInstance.getMerchantSchemaDetails(schemaId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MerchantApi#getMerchantSchemaDetails");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **schemaId** | **String**| The ID of the specific schema to get details for |

### Return type

[**MerchantSchemaDetailsResult**](MerchantSchemaDetailsResult.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |

<a name="getMerchantSchemas"></a>
# **getMerchantSchemas**
> MerchantSchemaResult getMerchantSchemas()

Get Schema List

Get the list of currently usable schema previously added for the merchant

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.client.ApiClient;
import au.com.woolworths.village.sdk.client.ApiException;
import au.com.woolworths.village.sdk.client.Configuration;
import au.com.woolworths.village.sdk.client.auth.*;
import au.com.woolworths.village.sdk.client.models.*;
import au.com.woolworths.village.sdk.api.MerchantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    MerchantApi apiInstance = new MerchantApi(defaultClient);
    try {
      MerchantSchemaResult result = apiInstance.getMerchantSchemas();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MerchantApi#getMerchantSchemas");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**MerchantSchemaResult**](MerchantSchemaResult.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |

<a name="getMerchantTransactionDetails"></a>
# **getMerchantTransactionDetails**
> GetMerchantTransactionDetailsResults getMerchantTransactionDetails(transactionId)

Get Transaction Details

Get the details for a specific transaction previously executed with the merchant.  Note that amounts are relative to the merchant.  A positive amount is a positive amount transferred to a merchant

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.client.ApiClient;
import au.com.woolworths.village.sdk.client.ApiException;
import au.com.woolworths.village.sdk.client.Configuration;
import au.com.woolworths.village.sdk.client.auth.*;
import au.com.woolworths.village.sdk.client.models.*;
import au.com.woolworths.village.sdk.api.MerchantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    MerchantApi apiInstance = new MerchantApi(defaultClient);
    String transactionId = "transactionId_example"; // String | The ID of the specific transaction
    try {
      GetMerchantTransactionDetailsResults result = apiInstance.getMerchantTransactionDetails(transactionId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MerchantApi#getMerchantTransactionDetails");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **transactionId** | **String**| The ID of the specific transaction |

### Return type

[**GetMerchantTransactionDetailsResults**](GetMerchantTransactionDetailsResults.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |

<a name="getMerchantTransactions"></a>
# **getMerchantTransactions**
> GetMerchantTransactionsResults getMerchantTransactions(startTime, endTime, pageSize, page)

Get Transaction List

Get a list of the previously executed transactions with the merchant.  Note that amounts are relative to the merchant.  A positive amount is a positive amount transferred to a merchant

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.client.ApiClient;
import au.com.woolworths.village.sdk.client.ApiException;
import au.com.woolworths.village.sdk.client.Configuration;
import au.com.woolworths.village.sdk.client.auth.*;
import au.com.woolworths.village.sdk.client.models.*;
import au.com.woolworths.village.sdk.api.MerchantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    MerchantApi apiInstance = new MerchantApi(defaultClient);
    String startTime = "startTime_example"; // String | If present, the date/time to limit transactions returned.  Transactions older than this time will not be returned
    String endTime = "endTime_example"; // String | If present, the date/time to limit transactions returned.  Transactions newer than this time will not be returned
    Integer pageSize = 25; // Integer | The number of records to return for this page.  Defaults to 25 if absent
    Integer page = 1; // Integer | The page of results to return with 1 indicating the first page.  Defaults to 1 if absent
    try {
      GetMerchantTransactionsResults result = apiInstance.getMerchantTransactions(startTime, endTime, pageSize, page);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MerchantApi#getMerchantTransactions");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **startTime** | **String**| If present, the date/time to limit transactions returned.  Transactions older than this time will not be returned | [optional]
 **endTime** | **String**| If present, the date/time to limit transactions returned.  Transactions newer than this time will not be returned | [optional]
 **pageSize** | **Integer**| The number of records to return for this page.  Defaults to 25 if absent | [optional] [default to 25]
 **page** | **Integer**| The page of results to return with 1 indicating the first page.  Defaults to 1 if absent | [optional] [default to 1]

### Return type

[**GetMerchantTransactionsResults**](GetMerchantTransactionsResults.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |

<a name="getPaymentQRCodeContent"></a>
# **getPaymentQRCodeContent**
> CreatePaymentQRCodeResults getPaymentQRCodeContent(qrId)

Get QR Code Content

Obtain the content or an image for an existing QR code.  If requested content type is application/json then a payload will be returned.  if requested content is image/png an image will be returned

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.client.ApiClient;
import au.com.woolworths.village.sdk.client.ApiException;
import au.com.woolworths.village.sdk.client.Configuration;
import au.com.woolworths.village.sdk.client.auth.*;
import au.com.woolworths.village.sdk.client.models.*;
import au.com.woolworths.village.sdk.api.MerchantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    MerchantApi apiInstance = new MerchantApi(defaultClient);
    String qrId = "qrId_example"; // String | The ID of the specific QR Code
    try {
      CreatePaymentQRCodeResults result = apiInstance.getPaymentQRCodeContent(qrId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MerchantApi#getPaymentQRCodeContent");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qrId** | **String**| The ID of the specific QR Code |

### Return type

[**CreatePaymentQRCodeResults**](CreatePaymentQRCodeResults.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json, image/png

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |
**400** | The specified payment request doesn&#39;t exist or has already been closed |  -  |

<a name="refundMerchantTransaction"></a>
# **refundMerchantTransaction**
> RefundMerchantTransactionResults refundMerchantTransaction(transactionId, refundMerchantTransactionRequest)

Refund Transaction

Refund a previously executed transaction

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.client.ApiClient;
import au.com.woolworths.village.sdk.client.ApiException;
import au.com.woolworths.village.sdk.client.Configuration;
import au.com.woolworths.village.sdk.client.auth.*;
import au.com.woolworths.village.sdk.client.models.*;
import au.com.woolworths.village.sdk.api.MerchantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    MerchantApi apiInstance = new MerchantApi(defaultClient);
    String transactionId = "transactionId_example"; // String | The ID of the specific transaction to reverse
    RefundMerchantTransactionRequest refundMerchantTransactionRequest = new RefundMerchantTransactionRequest(); // RefundMerchantTransactionRequest | 
    try {
      RefundMerchantTransactionResults result = apiInstance.refundMerchantTransaction(transactionId, refundMerchantTransactionRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MerchantApi#refundMerchantTransaction");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **transactionId** | **String**| The ID of the specific transaction to reverse |
 **refundMerchantTransactionRequest** | [**RefundMerchantTransactionRequest**](RefundMerchantTransactionRequest.md)|  |

### Return type

[**RefundMerchantTransactionResults**](RefundMerchantTransactionResults.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |
**400** | The transaction specified doesn&#39;t exist or is a refund transaction already |  -  |

<a name="setMerchantPreferences"></a>
# **setMerchantPreferences**
> setMerchantPreferences(merchantPreferences)

Set Preferences

Change the preferences for the customer or merchant (depending on calling identity)

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.client.ApiClient;
import au.com.woolworths.village.sdk.client.ApiException;
import au.com.woolworths.village.sdk.client.Configuration;
import au.com.woolworths.village.sdk.client.auth.*;
import au.com.woolworths.village.sdk.client.models.*;
import au.com.woolworths.village.sdk.api.MerchantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    MerchantApi apiInstance = new MerchantApi(defaultClient);
    MerchantPreferences merchantPreferences = new MerchantPreferences(); // MerchantPreferences | 
    try {
      apiInstance.setMerchantPreferences(merchantPreferences);
    } catch (ApiException e) {
      System.err.println("Exception when calling MerchantApi#setMerchantPreferences");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **merchantPreferences** | [**MerchantPreferences**](MerchantPreferences.md)|  |

### Return type

null (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**204** | Preferences successfully updated.  No content returned |  -  |

