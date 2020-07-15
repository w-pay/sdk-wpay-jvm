# MerchantApi

All URIs are relative to *http://localhost:3000*

Method | HTTP request | Description
------------- | ------------- | -------------
[**cancelPaymentQRCode**](MerchantApi.md#cancelPaymentQRCode) | **DELETE** /merchant/qr/{qrId} | Invalidate QR Code
[**createCustomerPaymentSession**](MerchantApi.md#createCustomerPaymentSession) | **POST** /merchant/payment/session | Create Payment Session
[**createMerchantSchema**](MerchantApi.md#createMerchantSchema) | **POST** /merchant/schema | Add Schema
[**createPaymentQRCode**](MerchantApi.md#createPaymentQRCode) | **POST** /merchant/qr | Create QR Code
[**createPaymentRequest**](MerchantApi.md#createPaymentRequest) | **POST** /merchant/payments | Create Payment
[**deleteMerchantPayment**](MerchantApi.md#deleteMerchantPayment) | **DELETE** /merchant/payments/{paymentRequestId} | Delete Payment
[**deleteMerchantPaymentSession**](MerchantApi.md#deleteMerchantPaymentSession) | **DELETE** /merchant/payment/session/{paymentSessionId} | Delete Payment Session
[**getMerchantPaymentDetails**](MerchantApi.md#getMerchantPaymentDetails) | **GET** /merchant/payments/{paymentRequestId} | Get Payment Details
[**getMerchantPaymentSession**](MerchantApi.md#getMerchantPaymentSession) | **GET** /merchant/payment/session/{paymentSessionId} | Get Payment Session
[**getMerchantPayments**](MerchantApi.md#getMerchantPayments) | **GET** /merchant/payments | Get Payment List
[**getMerchantPreferences**](MerchantApi.md#getMerchantPreferences) | **GET** /merchant/preferences | Get Preferences
[**getMerchantSchemaDetails**](MerchantApi.md#getMerchantSchemaDetails) | **GET** /merchant/schema/{schemaId} | Get Schema Details
[**getMerchantSchemas**](MerchantApi.md#getMerchantSchemas) | **GET** /merchant/schema | Get Schema List
[**getMerchantTransactionDetails**](MerchantApi.md#getMerchantTransactionDetails) | **GET** /merchant/transactions/{transactionId} | Get Transaction Details
[**getMerchantTransactions**](MerchantApi.md#getMerchantTransactions) | **GET** /merchant/transactions | Get Transaction List
[**getPaymentQRCodeContent**](MerchantApi.md#getPaymentQRCodeContent) | **GET** /merchant/qr/{qrId} | Get QR Code Content
[**refundMerchantTransaction**](MerchantApi.md#refundMerchantTransaction) | **POST** /merchant/transactions/{transactionId}/refund | Refund Transaction
[**setMerchantPreferences**](MerchantApi.md#setMerchantPreferences) | **POST** /merchant/preferences | Set Preferences
[**updateMerchantPaymentSession**](MerchantApi.md#updateMerchantPaymentSession) | **POST** /merchant/payment/session/{paymentSessionId} | Update Payment Session


<a name="cancelPaymentQRCode"></a>
# **cancelPaymentQRCode**
> cancelPaymentQRCode(xMerchantID, qrId)

Invalidate QR Code

Cancel an existing QC code.  Effectively expires the QR code

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.openapi.client.ApiClient;
import au.com.woolworths.village.sdk.openapi.client.ApiException;
import au.com.woolworths.village.sdk.openapi.client.Configuration;
import au.com.woolworths.village.sdk.openapi.client.auth.*;
import au.com.woolworths.village.sdk.openapi.client.models.*;
import au.com.woolworths.village.sdk.openapi.api.MerchantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    MerchantApi apiInstance = new MerchantApi(defaultClient);
    String xMerchantID = 10001; // String | 
    String qrId = dca8edc5-bbb7-44c0-8056-a5daf4327601; // String | The ID of the specific QR Code
    try {
      apiInstance.cancelPaymentQRCode(xMerchantID, qrId);
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
 **xMerchantID** | **String**|  |
 **qrId** | **String**| The ID of the specific QR Code |

### Return type

null (empty response body)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**204** | QR code has been successfully expired.  Not content returned |  -  |
**400** | The QR code specified doesn&#39;t exist |  -  |

<a name="createCustomerPaymentSession"></a>
# **createCustomerPaymentSession**
> CreateMerchantPaymentSessionResponse createCustomerPaymentSession(xMerchantID, createPaymentSessionRequest)

Create Payment Session

Create a new payment session

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.openapi.client.ApiClient;
import au.com.woolworths.village.sdk.openapi.client.ApiException;
import au.com.woolworths.village.sdk.openapi.client.Configuration;
import au.com.woolworths.village.sdk.openapi.client.auth.*;
import au.com.woolworths.village.sdk.openapi.client.models.*;
import au.com.woolworths.village.sdk.openapi.api.MerchantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    MerchantApi apiInstance = new MerchantApi(defaultClient);
    String xMerchantID = 10001; // String | 
    CreatePaymentSessionRequest createPaymentSessionRequest = new CreatePaymentSessionRequest(); // CreatePaymentSessionRequest | 
    try {
      CreateMerchantPaymentSessionResponse result = apiInstance.createCustomerPaymentSession(xMerchantID, createPaymentSessionRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MerchantApi#createCustomerPaymentSession");
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
 **xMerchantID** | **String**|  |
 **createPaymentSessionRequest** | [**CreatePaymentSessionRequest**](CreatePaymentSessionRequest.md)|  |

### Return type

[**CreateMerchantPaymentSessionResponse**](CreateMerchantPaymentSessionResponse.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |

<a name="createMerchantSchema"></a>
# **createMerchantSchema**
> CreateMerchantSchemaResults createMerchantSchema(xMerchantID, merchantSchema)

Add Schema

Add a new schema that can be used in schema based payloads for this merchant

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.openapi.client.ApiClient;
import au.com.woolworths.village.sdk.openapi.client.ApiException;
import au.com.woolworths.village.sdk.openapi.client.Configuration;
import au.com.woolworths.village.sdk.openapi.client.auth.*;
import au.com.woolworths.village.sdk.openapi.client.models.*;
import au.com.woolworths.village.sdk.openapi.api.MerchantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    MerchantApi apiInstance = new MerchantApi(defaultClient);
    String xMerchantID = 10001; // String | 
    MerchantSchema merchantSchema = new MerchantSchema(); // MerchantSchema | 
    try {
      CreateMerchantSchemaResults result = apiInstance.createMerchantSchema(xMerchantID, merchantSchema);
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
 **xMerchantID** | **String**|  |
 **merchantSchema** | [**MerchantSchema**](MerchantSchema.md)|  |

### Return type

[**CreateMerchantSchemaResults**](CreateMerchantSchemaResults.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |

<a name="createPaymentQRCode"></a>
# **createPaymentQRCode**
> CreatePaymentQRCodeResults createPaymentQRCode(xMerchantID, paymentQRCodeDetails)

Create QR Code

Create a new QR code for an existing payment

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.openapi.client.ApiClient;
import au.com.woolworths.village.sdk.openapi.client.ApiException;
import au.com.woolworths.village.sdk.openapi.client.Configuration;
import au.com.woolworths.village.sdk.openapi.client.auth.*;
import au.com.woolworths.village.sdk.openapi.client.models.*;
import au.com.woolworths.village.sdk.openapi.api.MerchantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    MerchantApi apiInstance = new MerchantApi(defaultClient);
    String xMerchantID = 10001; // String | 
    PaymentQRCodeDetails paymentQRCodeDetails = new PaymentQRCodeDetails(); // PaymentQRCodeDetails | 
    try {
      CreatePaymentQRCodeResults result = apiInstance.createPaymentQRCode(xMerchantID, paymentQRCodeDetails);
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
 **xMerchantID** | **String**|  |
 **paymentQRCodeDetails** | [**PaymentQRCodeDetails**](PaymentQRCodeDetails.md)|  |

### Return type

[**CreatePaymentQRCodeResults**](CreatePaymentQRCodeResults.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

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
> CreatePaymentRequestResults createPaymentRequest(xMerchantID, merchantPaymentRequest)

Create Payment

Create a new payment request that can then be presented to a customer for payment

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.openapi.client.ApiClient;
import au.com.woolworths.village.sdk.openapi.client.ApiException;
import au.com.woolworths.village.sdk.openapi.client.Configuration;
import au.com.woolworths.village.sdk.openapi.client.auth.*;
import au.com.woolworths.village.sdk.openapi.client.models.*;
import au.com.woolworths.village.sdk.openapi.api.MerchantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    MerchantApi apiInstance = new MerchantApi(defaultClient);
    String xMerchantID = 10001; // String | 
    MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest(); // MerchantPaymentRequest | 
    try {
      CreatePaymentRequestResults result = apiInstance.createPaymentRequest(xMerchantID, merchantPaymentRequest);
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
 **xMerchantID** | **String**|  |
 **merchantPaymentRequest** | [**MerchantPaymentRequest**](MerchantPaymentRequest.md)|  |

### Return type

[**CreatePaymentRequestResults**](CreatePaymentRequestResults.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |

<a name="deleteMerchantPayment"></a>
# **deleteMerchantPayment**
> deleteMerchantPayment(xMerchantID, paymentRequestId)

Delete Payment

Cancel an existing payment by setting the expiration date/time to now and setting the remaining uses to 0.  Will only be successful if the payment is still pending.  Completed payments need to be refunded using the dedicated API for that purpose

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.openapi.client.ApiClient;
import au.com.woolworths.village.sdk.openapi.client.ApiException;
import au.com.woolworths.village.sdk.openapi.client.Configuration;
import au.com.woolworths.village.sdk.openapi.client.auth.*;
import au.com.woolworths.village.sdk.openapi.client.models.*;
import au.com.woolworths.village.sdk.openapi.api.MerchantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    MerchantApi apiInstance = new MerchantApi(defaultClient);
    String xMerchantID = 10001; // String | 
    String paymentRequestId = "paymentRequestId_example"; // String | The ID of the specific payment request
    try {
      apiInstance.deleteMerchantPayment(xMerchantID, paymentRequestId);
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
 **xMerchantID** | **String**|  |
 **paymentRequestId** | **String**| The ID of the specific payment request |

### Return type

null (empty response body)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**204** | Payment request has been successfully expired.  Not content returned |  -  |
**400** | The payment request specified doesn&#39;t exist |  -  |

<a name="deleteMerchantPaymentSession"></a>
# **deleteMerchantPaymentSession**
> deleteMerchantPaymentSession(xMerchantID, paymentSessionId)

Delete Payment Session

Deletes a payment session by setting its expiration date/time to now

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.openapi.client.ApiClient;
import au.com.woolworths.village.sdk.openapi.client.ApiException;
import au.com.woolworths.village.sdk.openapi.client.Configuration;
import au.com.woolworths.village.sdk.openapi.client.auth.*;
import au.com.woolworths.village.sdk.openapi.client.models.*;
import au.com.woolworths.village.sdk.openapi.api.MerchantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    MerchantApi apiInstance = new MerchantApi(defaultClient);
    String xMerchantID = 10001; // String | 
    String paymentSessionId = "paymentSessionId_example"; // String | The ID of the specific payment session to delete
    try {
      apiInstance.deleteMerchantPaymentSession(xMerchantID, paymentSessionId);
    } catch (ApiException e) {
      System.err.println("Exception when calling MerchantApi#deleteMerchantPaymentSession");
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
 **xMerchantID** | **String**|  |
 **paymentSessionId** | **String**| The ID of the specific payment session to delete |

### Return type

null (empty response body)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**204** | The payment session has been successfully deleted. No content returned. |  -  |

<a name="getMerchantPaymentDetails"></a>
# **getMerchantPaymentDetails**
> GetMerchantPaymentDetailsResults getMerchantPaymentDetails(xMerchantID, paymentRequestId)

Get Payment Details

Get the details for a specific payment.  Provides all types of payments and all payment content

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.openapi.client.ApiClient;
import au.com.woolworths.village.sdk.openapi.client.ApiException;
import au.com.woolworths.village.sdk.openapi.client.Configuration;
import au.com.woolworths.village.sdk.openapi.client.auth.*;
import au.com.woolworths.village.sdk.openapi.client.models.*;
import au.com.woolworths.village.sdk.openapi.api.MerchantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    MerchantApi apiInstance = new MerchantApi(defaultClient);
    String xMerchantID = 10001; // String | 
    String paymentRequestId = "paymentRequestId_example"; // String | The ID of the specific payment request
    try {
      GetMerchantPaymentDetailsResults result = apiInstance.getMerchantPaymentDetails(xMerchantID, paymentRequestId);
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
 **xMerchantID** | **String**|  |
 **paymentRequestId** | **String**| The ID of the specific payment request |

### Return type

[**GetMerchantPaymentDetailsResults**](GetMerchantPaymentDetailsResults.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |
**400** | The specified Payment Request ID doesn&#39;t exist |  -  |

<a name="getMerchantPaymentSession"></a>
# **getMerchantPaymentSession**
> CustomerPaymentSessionResult getMerchantPaymentSession(xMerchantID, paymentSessionId)

Get Payment Session

Get the details of a payment session

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.openapi.client.ApiClient;
import au.com.woolworths.village.sdk.openapi.client.ApiException;
import au.com.woolworths.village.sdk.openapi.client.Configuration;
import au.com.woolworths.village.sdk.openapi.client.auth.*;
import au.com.woolworths.village.sdk.openapi.client.models.*;
import au.com.woolworths.village.sdk.openapi.api.MerchantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    MerchantApi apiInstance = new MerchantApi(defaultClient);
    String xMerchantID = 10001; // String | 
    String paymentSessionId = "paymentSessionId_example"; // String | The ID of the specific payment session to retrieve
    try {
      CustomerPaymentSessionResult result = apiInstance.getMerchantPaymentSession(xMerchantID, paymentSessionId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MerchantApi#getMerchantPaymentSession");
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
 **xMerchantID** | **String**|  |
 **paymentSessionId** | **String**| The ID of the specific payment session to retrieve |

### Return type

[**CustomerPaymentSessionResult**](CustomerPaymentSessionResult.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |

<a name="getMerchantPayments"></a>
# **getMerchantPayments**
> GetMerchantPaymentsResults getMerchantPayments(xMerchantID, type, pageSize, page)

Get Payment List

Get a list of the payments initiated by the merchant, both pending and complete

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.openapi.client.ApiClient;
import au.com.woolworths.village.sdk.openapi.client.ApiException;
import au.com.woolworths.village.sdk.openapi.client.Configuration;
import au.com.woolworths.village.sdk.openapi.client.auth.*;
import au.com.woolworths.village.sdk.openapi.client.models.*;
import au.com.woolworths.village.sdk.openapi.api.MerchantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    MerchantApi apiInstance = new MerchantApi(defaultClient);
    String xMerchantID = 10001; // String | 
    String type = ACTIVE; // String | The type of payment requests to return: active, inactive or both
    Integer pageSize = 25; // Integer | The number of records to return for this page.  Defaults to 25 if absent
    Integer page = 1; // Integer | The page of results to return with 1 indicating the first page.  Defaults to 1 if absent
    try {
      GetMerchantPaymentsResults result = apiInstance.getMerchantPayments(xMerchantID, type, pageSize, page);
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
 **xMerchantID** | **String**|  |
 **type** | **String**| The type of payment requests to return: active, inactive or both | [optional] [default to ACTIVE] [enum: ACTIVE, INACTIVE, ALL]
 **pageSize** | **Integer**| The number of records to return for this page.  Defaults to 25 if absent | [optional] [default to 25]
 **page** | **Integer**| The page of results to return with 1 indicating the first page.  Defaults to 1 if absent | [optional] [default to 1]

### Return type

[**GetMerchantPaymentsResults**](GetMerchantPaymentsResults.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |

<a name="getMerchantPreferences"></a>
# **getMerchantPreferences**
> CustomerPreferencesResult getMerchantPreferences(xMerchantID)

Get Preferences

Get the preferences previously set by the customer or merchant (depending on calling identity)

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.openapi.client.ApiClient;
import au.com.woolworths.village.sdk.openapi.client.ApiException;
import au.com.woolworths.village.sdk.openapi.client.Configuration;
import au.com.woolworths.village.sdk.openapi.client.auth.*;
import au.com.woolworths.village.sdk.openapi.client.models.*;
import au.com.woolworths.village.sdk.openapi.api.MerchantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    MerchantApi apiInstance = new MerchantApi(defaultClient);
    String xMerchantID = 10001; // String | 
    try {
      CustomerPreferencesResult result = apiInstance.getMerchantPreferences(xMerchantID);
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

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **xMerchantID** | **String**|  |

### Return type

[**CustomerPreferencesResult**](CustomerPreferencesResult.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |

<a name="getMerchantSchemaDetails"></a>
# **getMerchantSchemaDetails**
> MerchantSchemaDetailsResult getMerchantSchemaDetails(xMerchantID, schemaId)

Get Schema Details

Get the list of currently usable schema previously added for the merchant

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.openapi.client.ApiClient;
import au.com.woolworths.village.sdk.openapi.client.ApiException;
import au.com.woolworths.village.sdk.openapi.client.Configuration;
import au.com.woolworths.village.sdk.openapi.client.auth.*;
import au.com.woolworths.village.sdk.openapi.client.models.*;
import au.com.woolworths.village.sdk.openapi.api.MerchantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    MerchantApi apiInstance = new MerchantApi(defaultClient);
    String xMerchantID = 10001; // String | 
    String schemaId = "schemaId_example"; // String | The ID of the specific schema to get details for
    try {
      MerchantSchemaDetailsResult result = apiInstance.getMerchantSchemaDetails(xMerchantID, schemaId);
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
 **xMerchantID** | **String**|  |
 **schemaId** | **String**| The ID of the specific schema to get details for |

### Return type

[**MerchantSchemaDetailsResult**](MerchantSchemaDetailsResult.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |

<a name="getMerchantSchemas"></a>
# **getMerchantSchemas**
> MerchantSchemaResult getMerchantSchemas(xMerchantID)

Get Schema List

Get the list of currently usable schema previously added for the merchant

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.openapi.client.ApiClient;
import au.com.woolworths.village.sdk.openapi.client.ApiException;
import au.com.woolworths.village.sdk.openapi.client.Configuration;
import au.com.woolworths.village.sdk.openapi.client.auth.*;
import au.com.woolworths.village.sdk.openapi.client.models.*;
import au.com.woolworths.village.sdk.openapi.api.MerchantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    MerchantApi apiInstance = new MerchantApi(defaultClient);
    String xMerchantID = 10001; // String | 
    try {
      MerchantSchemaResult result = apiInstance.getMerchantSchemas(xMerchantID);
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

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **xMerchantID** | **String**|  |

### Return type

[**MerchantSchemaResult**](MerchantSchemaResult.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |

<a name="getMerchantTransactionDetails"></a>
# **getMerchantTransactionDetails**
> GetMerchantTransactionDetailsResults getMerchantTransactionDetails(xMerchantID, transactionId)

Get Transaction Details

Get the details for a specific transaction previously executed with the merchant.  Note that amounts are relative to the merchant.  A positive amount is a positive amount transferred to a merchant

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.openapi.client.ApiClient;
import au.com.woolworths.village.sdk.openapi.client.ApiException;
import au.com.woolworths.village.sdk.openapi.client.Configuration;
import au.com.woolworths.village.sdk.openapi.client.auth.*;
import au.com.woolworths.village.sdk.openapi.client.models.*;
import au.com.woolworths.village.sdk.openapi.api.MerchantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    MerchantApi apiInstance = new MerchantApi(defaultClient);
    String xMerchantID = 10001; // String | 
    String transactionId = "transactionId_example"; // String | The ID of the specific transaction
    try {
      GetMerchantTransactionDetailsResults result = apiInstance.getMerchantTransactionDetails(xMerchantID, transactionId);
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
 **xMerchantID** | **String**|  |
 **transactionId** | **String**| The ID of the specific transaction |

### Return type

[**GetMerchantTransactionDetailsResults**](GetMerchantTransactionDetailsResults.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |

<a name="getMerchantTransactions"></a>
# **getMerchantTransactions**
> GetMerchantTransactionsResults getMerchantTransactions(xMerchantID, startTime, endTime, pageSize, page)

Get Transaction List

Get a list of the previously executed transactions with the merchant.  Note that amounts are relative to the merchant.  A positive amount is a positive amount transferred to a merchant

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.openapi.client.ApiClient;
import au.com.woolworths.village.sdk.openapi.client.ApiException;
import au.com.woolworths.village.sdk.openapi.client.Configuration;
import au.com.woolworths.village.sdk.openapi.client.auth.*;
import au.com.woolworths.village.sdk.openapi.client.models.*;
import au.com.woolworths.village.sdk.openapi.api.MerchantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    MerchantApi apiInstance = new MerchantApi(defaultClient);
    String xMerchantID = 10001; // String | 
    OffsetDateTime startTime = 2017-11-06T19:38:09.890+11:00; // OffsetDateTime | If present, the date/time to limit transactions returned.  Transactions older than this time will not be returned
    OffsetDateTime endTime = 2017-11-06T19:38:09.890+11:00; // OffsetDateTime | If present, the date/time to limit transactions returned.  Transactions newer than this time will not be returned
    Integer pageSize = 25; // Integer | The number of records to return for this page.  Defaults to 25 if absent
    Integer page = 1; // Integer | The page of results to return with 1 indicating the first page.  Defaults to 1 if absent
    try {
      GetMerchantTransactionsResults result = apiInstance.getMerchantTransactions(xMerchantID, startTime, endTime, pageSize, page);
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
 **xMerchantID** | **String**|  |
 **startTime** | **OffsetDateTime**| If present, the date/time to limit transactions returned.  Transactions older than this time will not be returned | [optional]
 **endTime** | **OffsetDateTime**| If present, the date/time to limit transactions returned.  Transactions newer than this time will not be returned | [optional]
 **pageSize** | **Integer**| The number of records to return for this page.  Defaults to 25 if absent | [optional] [default to 25]
 **page** | **Integer**| The page of results to return with 1 indicating the first page.  Defaults to 1 if absent | [optional] [default to 1]

### Return type

[**GetMerchantTransactionsResults**](GetMerchantTransactionsResults.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |

<a name="getPaymentQRCodeContent"></a>
# **getPaymentQRCodeContent**
> CreatePaymentQRCodeResults getPaymentQRCodeContent(xMerchantID, qrId)

Get QR Code Content

Obtain the content or an image for an existing QR code.  If requested content type is application/json then a payload will be returned.  if requested content is image/png an image will be returned

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.openapi.client.ApiClient;
import au.com.woolworths.village.sdk.openapi.client.ApiException;
import au.com.woolworths.village.sdk.openapi.client.Configuration;
import au.com.woolworths.village.sdk.openapi.client.auth.*;
import au.com.woolworths.village.sdk.openapi.client.models.*;
import au.com.woolworths.village.sdk.openapi.api.MerchantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    MerchantApi apiInstance = new MerchantApi(defaultClient);
    String xMerchantID = 10001; // String | 
    String qrId = "qrId_example"; // String | The ID of the specific QR Code
    try {
      CreatePaymentQRCodeResults result = apiInstance.getPaymentQRCodeContent(xMerchantID, qrId);
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
 **xMerchantID** | **String**|  |
 **qrId** | **String**| The ID of the specific QR Code |

### Return type

[**CreatePaymentQRCodeResults**](CreatePaymentQRCodeResults.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

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
> RefundMerchantTransactionResults refundMerchantTransaction(xMerchantID, transactionId, refundMerchantTransactionRequest)

Refund Transaction

Refund a previously executed transaction

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.openapi.client.ApiClient;
import au.com.woolworths.village.sdk.openapi.client.ApiException;
import au.com.woolworths.village.sdk.openapi.client.Configuration;
import au.com.woolworths.village.sdk.openapi.client.auth.*;
import au.com.woolworths.village.sdk.openapi.client.models.*;
import au.com.woolworths.village.sdk.openapi.api.MerchantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    MerchantApi apiInstance = new MerchantApi(defaultClient);
    String xMerchantID = 10001; // String | 
    String transactionId = "transactionId_example"; // String | The ID of the specific transaction to reverse
    RefundMerchantTransactionRequest refundMerchantTransactionRequest = new RefundMerchantTransactionRequest(); // RefundMerchantTransactionRequest | 
    try {
      RefundMerchantTransactionResults result = apiInstance.refundMerchantTransaction(xMerchantID, transactionId, refundMerchantTransactionRequest);
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
 **xMerchantID** | **String**|  |
 **transactionId** | **String**| The ID of the specific transaction to reverse |
 **refundMerchantTransactionRequest** | [**RefundMerchantTransactionRequest**](RefundMerchantTransactionRequest.md)|  |

### Return type

[**RefundMerchantTransactionResults**](RefundMerchantTransactionResults.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

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
> setMerchantPreferences(xMerchantID, merchantPreferences)

Set Preferences

Change the preferences for the customer or merchant (depending on calling identity)

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.openapi.client.ApiClient;
import au.com.woolworths.village.sdk.openapi.client.ApiException;
import au.com.woolworths.village.sdk.openapi.client.Configuration;
import au.com.woolworths.village.sdk.openapi.client.auth.*;
import au.com.woolworths.village.sdk.openapi.client.models.*;
import au.com.woolworths.village.sdk.openapi.api.MerchantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    MerchantApi apiInstance = new MerchantApi(defaultClient);
    String xMerchantID = 10001; // String | 
    MerchantPreferences merchantPreferences = new MerchantPreferences(); // MerchantPreferences | 
    try {
      apiInstance.setMerchantPreferences(xMerchantID, merchantPreferences);
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
 **xMerchantID** | **String**|  |
 **merchantPreferences** | [**MerchantPreferences**](MerchantPreferences.md)|  |

### Return type

null (empty response body)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**204** | Preferences successfully updated.  No content returned |  -  |

<a name="updateMerchantPaymentSession"></a>
# **updateMerchantPaymentSession**
> updateMerchantPaymentSession(xMerchantID, paymentSessionId, updatePaymentSessionRequest1)

Update Payment Session

Update the payment session details

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.openapi.client.ApiClient;
import au.com.woolworths.village.sdk.openapi.client.ApiException;
import au.com.woolworths.village.sdk.openapi.client.Configuration;
import au.com.woolworths.village.sdk.openapi.client.auth.*;
import au.com.woolworths.village.sdk.openapi.client.models.*;
import au.com.woolworths.village.sdk.openapi.api.MerchantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    MerchantApi apiInstance = new MerchantApi(defaultClient);
    String xMerchantID = 10001; // String | 
    String paymentSessionId = "paymentSessionId_example"; // String | The ID of the specific payment session to retrieve
    UpdatePaymentSessionRequest1 updatePaymentSessionRequest1 = new UpdatePaymentSessionRequest1(); // UpdatePaymentSessionRequest1 | 
    try {
      apiInstance.updateMerchantPaymentSession(xMerchantID, paymentSessionId, updatePaymentSessionRequest1);
    } catch (ApiException e) {
      System.err.println("Exception when calling MerchantApi#updateMerchantPaymentSession");
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
 **xMerchantID** | **String**|  |
 **paymentSessionId** | **String**| The ID of the specific payment session to retrieve |
 **updatePaymentSessionRequest1** | [**UpdatePaymentSessionRequest1**](UpdatePaymentSessionRequest1.md)|  |

### Return type

null (empty response body)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**204** | The payment session has been successfully updated. No content returned. |  -  |

