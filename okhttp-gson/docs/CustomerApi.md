# CustomerApi

All URIs are relative to *http://localhost:3000*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getCustomerPaymentDetailsByPaymentId**](CustomerApi.md#getCustomerPaymentDetailsByPaymentId) | **GET** /customer/payments/{paymentRequestId} | Get Payment Details
[**getCustomerPaymentDetailsByQRCodeId**](CustomerApi.md#getCustomerPaymentDetailsByQRCodeId) | **GET** /customer/qr/{qrId} | Get Payment From QR
[**getCustomerPaymentInstruments**](CustomerApi.md#getCustomerPaymentInstruments) | **GET** /customer/instruments | Get Payment Instruments
[**getCustomerPaymentSession**](CustomerApi.md#getCustomerPaymentSession) | **GET** /customer/payment/session/{paymentSessionId} | Get Payment Session
[**getCustomerPaymentSessionByQr**](CustomerApi.md#getCustomerPaymentSessionByQr) | **GET** /customer/payment/session/qr/{qrId} | Get Payment Session
[**getCustomerPreferences**](CustomerApi.md#getCustomerPreferences) | **GET** /customer/preferences | Get Preferences
[**getCustomerTransactionDetails**](CustomerApi.md#getCustomerTransactionDetails) | **GET** /customer/transactions/{transactionId} | Get Transaction Details
[**getCustomerTransactions**](CustomerApi.md#getCustomerTransactions) | **GET** /customer/transactions | Get Transaction List
[**initiatePaymentInstrumentAddition**](CustomerApi.md#initiatePaymentInstrumentAddition) | **POST** /customer/instruments | Initiate Instrument Addition
[**makeCustomerPayment**](CustomerApi.md#makeCustomerPayment) | **PUT** /customer/payments/{paymentRequestId} | Pay Payment
[**setCustomerPreferences**](CustomerApi.md#setCustomerPreferences) | **POST** /customer/preferences | Set Preferences
[**updateCustomerPaymentSession**](CustomerApi.md#updateCustomerPaymentSession) | **POST** /customer/payment/session/{paymentSessionId} | Update Payment Session


<a name="getCustomerPaymentDetailsByPaymentId"></a>
# **getCustomerPaymentDetailsByPaymentId**
> GetCustomerPaymentResult getCustomerPaymentDetailsByPaymentId(xWalletID, paymentRequestId)

Get Payment Details

Get the details for a specific payment request so that the customer can pay using it

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.openapi.client.ApiClient;
import au.com.woolworths.village.sdk.openapi.client.ApiException;
import au.com.woolworths.village.sdk.openapi.client.Configuration;
import au.com.woolworths.village.sdk.openapi.client.auth.*;
import au.com.woolworths.village.sdk.openapi.client.models.*;
import au.com.woolworths.village.sdk.openapi.api.CustomerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    CustomerApi apiInstance = new CustomerApi(defaultClient);
    String xWalletID = bb8f86af-9e7b-4659-85d5-346b5e99d500; // String | 
    String paymentRequestId = "paymentRequestId_example"; // String | The ID of the specific payment request
    try {
      GetCustomerPaymentResult result = apiInstance.getCustomerPaymentDetailsByPaymentId(xWalletID, paymentRequestId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CustomerApi#getCustomerPaymentDetailsByPaymentId");
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
 **xWalletID** | **String**|  |
 **paymentRequestId** | **String**| The ID of the specific payment request |

### Return type

[**GetCustomerPaymentResult**](GetCustomerPaymentResult.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth), [bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response. |  -  |
**400** | The specified Payment Request ID doesn&#39;t exist, has been used or is expired |  -  |

<a name="getCustomerPaymentDetailsByQRCodeId"></a>
# **getCustomerPaymentDetailsByQRCodeId**
> GetCustomerPaymentResult getCustomerPaymentDetailsByQRCodeId(xWalletID, qrId)

Get Payment From QR

Get the details for a specific payment from a QR Code ID so that the customer can pay using it

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.openapi.client.ApiClient;
import au.com.woolworths.village.sdk.openapi.client.ApiException;
import au.com.woolworths.village.sdk.openapi.client.Configuration;
import au.com.woolworths.village.sdk.openapi.client.auth.*;
import au.com.woolworths.village.sdk.openapi.client.models.*;
import au.com.woolworths.village.sdk.openapi.api.CustomerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    CustomerApi apiInstance = new CustomerApi(defaultClient);
    String xWalletID = bb8f86af-9e7b-4659-85d5-346b5e99d500; // String | 
    String qrId = "qrId_example"; // String | The ID of the specific QR Code
    try {
      GetCustomerPaymentResult result = apiInstance.getCustomerPaymentDetailsByQRCodeId(xWalletID, qrId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CustomerApi#getCustomerPaymentDetailsByQRCodeId");
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
 **xWalletID** | **String**|  |
 **qrId** | **String**| The ID of the specific QR Code |

### Return type

[**GetCustomerPaymentResult**](GetCustomerPaymentResult.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth), [bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response. |  -  |
**400** | The specified QR Code ID doesn&#39;t exist or has been expired or the underlying payment request is no longer usable |  -  |

<a name="getCustomerPaymentInstruments"></a>
# **getCustomerPaymentInstruments**
> GetCustomerPaymentInstrumentsResults getCustomerPaymentInstruments(xWalletID, xEverdayPayWallet)

Get Payment Instruments

Get the list of payment instruments currently configured for the customer.  Returns an array of instrument records that can be used to execute payments

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.openapi.client.ApiClient;
import au.com.woolworths.village.sdk.openapi.client.ApiException;
import au.com.woolworths.village.sdk.openapi.client.Configuration;
import au.com.woolworths.village.sdk.openapi.client.auth.*;
import au.com.woolworths.village.sdk.openapi.client.models.*;
import au.com.woolworths.village.sdk.openapi.api.CustomerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    CustomerApi apiInstance = new CustomerApi(defaultClient);
    String xWalletID = bb8f86af-9e7b-4659-85d5-346b5e99d500; // String | 
    Boolean xEverdayPayWallet = false; // Boolean | Includes everyday pay wallet in the list of payment instruments
    try {
      GetCustomerPaymentInstrumentsResults result = apiInstance.getCustomerPaymentInstruments(xWalletID, xEverdayPayWallet);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CustomerApi#getCustomerPaymentInstruments");
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
 **xWalletID** | **String**|  |
 **xEverdayPayWallet** | **Boolean**| Includes everyday pay wallet in the list of payment instruments | [optional] [default to false]

### Return type

[**GetCustomerPaymentInstrumentsResults**](GetCustomerPaymentInstrumentsResults.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth), [bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |

<a name="getCustomerPaymentSession"></a>
# **getCustomerPaymentSession**
> CustomerPaymentSessionResult getCustomerPaymentSession(xWalletID, paymentSessionId)

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
import au.com.woolworths.village.sdk.openapi.api.CustomerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    CustomerApi apiInstance = new CustomerApi(defaultClient);
    String xWalletID = bb8f86af-9e7b-4659-85d5-346b5e99d500; // String | 
    String paymentSessionId = "paymentSessionId_example"; // String | The ID of the specific payment session to retrieve
    try {
      CustomerPaymentSessionResult result = apiInstance.getCustomerPaymentSession(xWalletID, paymentSessionId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CustomerApi#getCustomerPaymentSession");
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
 **xWalletID** | **String**|  |
 **paymentSessionId** | **String**| The ID of the specific payment session to retrieve |

### Return type

[**CustomerPaymentSessionResult**](CustomerPaymentSessionResult.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth), [bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |

<a name="getCustomerPaymentSessionByQr"></a>
# **getCustomerPaymentSessionByQr**
> CustomerPaymentSessionResult getCustomerPaymentSessionByQr(xWalletID, qrId)

Get Payment Session

Get the payment session associated with the qrId

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.openapi.client.ApiClient;
import au.com.woolworths.village.sdk.openapi.client.ApiException;
import au.com.woolworths.village.sdk.openapi.client.Configuration;
import au.com.woolworths.village.sdk.openapi.client.auth.*;
import au.com.woolworths.village.sdk.openapi.client.models.*;
import au.com.woolworths.village.sdk.openapi.api.CustomerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    CustomerApi apiInstance = new CustomerApi(defaultClient);
    String xWalletID = bb8f86af-9e7b-4659-85d5-346b5e99d500; // String | 
    String qrId = "qrId_example"; // String | The ID of the qr relating to the payment session
    try {
      CustomerPaymentSessionResult result = apiInstance.getCustomerPaymentSessionByQr(xWalletID, qrId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CustomerApi#getCustomerPaymentSessionByQr");
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
 **xWalletID** | **String**|  |
 **qrId** | **String**| The ID of the qr relating to the payment session |

### Return type

[**CustomerPaymentSessionResult**](CustomerPaymentSessionResult.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth), [bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |

<a name="getCustomerPreferences"></a>
# **getCustomerPreferences**
> CustomerPreferencesResult getCustomerPreferences(xWalletID)

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
import au.com.woolworths.village.sdk.openapi.api.CustomerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    CustomerApi apiInstance = new CustomerApi(defaultClient);
    String xWalletID = bb8f86af-9e7b-4659-85d5-346b5e99d500; // String | 
    try {
      CustomerPreferencesResult result = apiInstance.getCustomerPreferences(xWalletID);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CustomerApi#getCustomerPreferences");
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
 **xWalletID** | **String**|  |

### Return type

[**CustomerPreferencesResult**](CustomerPreferencesResult.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth), [bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |

<a name="getCustomerTransactionDetails"></a>
# **getCustomerTransactionDetails**
> GetCustomerTransactionDetailsResults getCustomerTransactionDetails(xWalletID, transactionId)

Get Transaction Details

Get the details for a specific transaction previously executed by the customer.  Note that amounts are relative to the merchant.  A positive amount is a positive amount transferred to a merchant

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.openapi.client.ApiClient;
import au.com.woolworths.village.sdk.openapi.client.ApiException;
import au.com.woolworths.village.sdk.openapi.client.Configuration;
import au.com.woolworths.village.sdk.openapi.client.auth.*;
import au.com.woolworths.village.sdk.openapi.client.models.*;
import au.com.woolworths.village.sdk.openapi.api.CustomerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    CustomerApi apiInstance = new CustomerApi(defaultClient);
    String xWalletID = bb8f86af-9e7b-4659-85d5-346b5e99d500; // String | 
    String transactionId = 75ba5b0b-7e5d-47fe-9508-29ca69fdb1d5; // String | The ID of the specific transaction
    try {
      GetCustomerTransactionDetailsResults result = apiInstance.getCustomerTransactionDetails(xWalletID, transactionId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CustomerApi#getCustomerTransactionDetails");
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
 **xWalletID** | **String**|  |
 **transactionId** | **String**| The ID of the specific transaction |

### Return type

[**GetCustomerTransactionDetailsResults**](GetCustomerTransactionDetailsResults.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth), [bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |

<a name="getCustomerTransactions"></a>
# **getCustomerTransactions**
> GetCustomerTransactionsResult getCustomerTransactions(xWalletID, paymentRequestId, startTime, endTime, pageSize, page)

Get Transaction List

Get a list of the previously executed transactions for the customer.  Note that amounts are relative to the merchant.  A positive amount is a positive amount transferred to a merchant

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.openapi.client.ApiClient;
import au.com.woolworths.village.sdk.openapi.client.ApiException;
import au.com.woolworths.village.sdk.openapi.client.Configuration;
import au.com.woolworths.village.sdk.openapi.client.auth.*;
import au.com.woolworths.village.sdk.openapi.client.models.*;
import au.com.woolworths.village.sdk.openapi.api.CustomerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    CustomerApi apiInstance = new CustomerApi(defaultClient);
    String xWalletID = bb8f86af-9e7b-4659-85d5-346b5e99d500; // String | 
    String paymentRequestId = f27b0189-3449-4215-ab95-31c24e775a48; // String | If present, limits the list of transactions to those that relate to the payment request
    OffsetDateTime startTime = 2017-11-06T19:38:09.890+11:00; // OffsetDateTime | If present, the date/time to limit transactions returned.  Transactions older than this time will not be returned
    OffsetDateTime endTime = 2017-11-06T19:38:09.890+11:00; // OffsetDateTime | If present, the date/time to limit transactions returned.  Transactions newer than this time will not be returned
    Integer pageSize = 25; // Integer | The number of records to return for this page.  Defaults to 25 if absent
    Integer page = 1; // Integer | The page of results to return with 1 indicating the first page.  Defaults to 1 if absent
    try {
      GetCustomerTransactionsResult result = apiInstance.getCustomerTransactions(xWalletID, paymentRequestId, startTime, endTime, pageSize, page);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CustomerApi#getCustomerTransactions");
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
 **xWalletID** | **String**|  |
 **paymentRequestId** | **String**| If present, limits the list of transactions to those that relate to the payment request | [optional]
 **startTime** | **OffsetDateTime**| If present, the date/time to limit transactions returned.  Transactions older than this time will not be returned | [optional]
 **endTime** | **OffsetDateTime**| If present, the date/time to limit transactions returned.  Transactions newer than this time will not be returned | [optional]
 **pageSize** | **Integer**| The number of records to return for this page.  Defaults to 25 if absent | [optional] [default to 25]
 **page** | **Integer**| The page of results to return with 1 indicating the first page.  Defaults to 1 if absent | [optional] [default to 1]

### Return type

[**GetCustomerTransactionsResult**](GetCustomerTransactionsResult.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth), [bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |

<a name="initiatePaymentInstrumentAddition"></a>
# **initiatePaymentInstrumentAddition**
> InitiatePaymentInstrumentAdditionResults initiatePaymentInstrumentAddition(xWalletID, instrumentAdditionDetails, xEverdayPayWallet)

Initiate Instrument Addition

Initiate the addition of a new payment instrument for this customer.  This API returns a URL to be used to access the DigiPay IFrame based interface to request the customer to enter a payment instrument details.

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.openapi.client.ApiClient;
import au.com.woolworths.village.sdk.openapi.client.ApiException;
import au.com.woolworths.village.sdk.openapi.client.Configuration;
import au.com.woolworths.village.sdk.openapi.client.auth.*;
import au.com.woolworths.village.sdk.openapi.client.models.*;
import au.com.woolworths.village.sdk.openapi.api.CustomerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    CustomerApi apiInstance = new CustomerApi(defaultClient);
    String xWalletID = bb8f86af-9e7b-4659-85d5-346b5e99d500; // String | 
    InstrumentAdditionDetails instrumentAdditionDetails = new InstrumentAdditionDetails(); // InstrumentAdditionDetails | 
    Boolean xEverdayPayWallet = false; // Boolean | The payment instrument should be stored in the everyday pay wallet
    try {
      InitiatePaymentInstrumentAdditionResults result = apiInstance.initiatePaymentInstrumentAddition(xWalletID, instrumentAdditionDetails, xEverdayPayWallet);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CustomerApi#initiatePaymentInstrumentAddition");
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
 **xWalletID** | **String**|  |
 **instrumentAdditionDetails** | [**InstrumentAdditionDetails**](InstrumentAdditionDetails.md)|  |
 **xEverdayPayWallet** | **Boolean**| The payment instrument should be stored in the everyday pay wallet | [optional] [default to false]

### Return type

[**InitiatePaymentInstrumentAdditionResults**](InitiatePaymentInstrumentAdditionResults.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth), [bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |

<a name="makeCustomerPayment"></a>
# **makeCustomerPayment**
> MakeCustomerPaymentResults makeCustomerPayment(xWalletID, paymentRequestId, customerPaymentDetails, xEverdayPayWallet)

Pay Payment

Pay a specific payment using the instrument details provided

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.openapi.client.ApiClient;
import au.com.woolworths.village.sdk.openapi.client.ApiException;
import au.com.woolworths.village.sdk.openapi.client.Configuration;
import au.com.woolworths.village.sdk.openapi.client.auth.*;
import au.com.woolworths.village.sdk.openapi.client.models.*;
import au.com.woolworths.village.sdk.openapi.api.CustomerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    CustomerApi apiInstance = new CustomerApi(defaultClient);
    String xWalletID = bb8f86af-9e7b-4659-85d5-346b5e99d500; // String | 
    String paymentRequestId = "paymentRequestId_example"; // String | The ID of the specific payment request
    CustomerPaymentDetails customerPaymentDetails = new CustomerPaymentDetails(); // CustomerPaymentDetails | 
    Boolean xEverdayPayWallet = false; // Boolean | The makes instruments available in the everyday pay wallet available for payments
    try {
      MakeCustomerPaymentResults result = apiInstance.makeCustomerPayment(xWalletID, paymentRequestId, customerPaymentDetails, xEverdayPayWallet);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CustomerApi#makeCustomerPayment");
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
 **xWalletID** | **String**|  |
 **paymentRequestId** | **String**| The ID of the specific payment request |
 **customerPaymentDetails** | [**CustomerPaymentDetails**](CustomerPaymentDetails.md)|  |
 **xEverdayPayWallet** | **Boolean**| The makes instruments available in the everyday pay wallet available for payments | [optional] [default to false]

### Return type

[**MakeCustomerPaymentResults**](MakeCustomerPaymentResults.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth), [bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |

<a name="setCustomerPreferences"></a>
# **setCustomerPreferences**
> setCustomerPreferences(xWalletID, customerPreferences)

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
import au.com.woolworths.village.sdk.openapi.api.CustomerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    CustomerApi apiInstance = new CustomerApi(defaultClient);
    String xWalletID = bb8f86af-9e7b-4659-85d5-346b5e99d500; // String | 
    CustomerPreferences customerPreferences = new CustomerPreferences(); // CustomerPreferences | 
    try {
      apiInstance.setCustomerPreferences(xWalletID, customerPreferences);
    } catch (ApiException e) {
      System.err.println("Exception when calling CustomerApi#setCustomerPreferences");
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
 **xWalletID** | **String**|  |
 **customerPreferences** | [**CustomerPreferences**](CustomerPreferences.md)|  |

### Return type

null (empty response body)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth), [bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**204** | Preferences successfully updated.  No content returned |  -  |

<a name="updateCustomerPaymentSession"></a>
# **updateCustomerPaymentSession**
> updateCustomerPaymentSession(xWalletID, paymentSessionId, updatePaymentSessionRequest)

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
import au.com.woolworths.village.sdk.openapi.api.CustomerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    CustomerApi apiInstance = new CustomerApi(defaultClient);
    String xWalletID = bb8f86af-9e7b-4659-85d5-346b5e99d500; // String | 
    String paymentSessionId = "paymentSessionId_example"; // String | The ID of the specific payment session to retrieve
    UpdatePaymentSessionRequest updatePaymentSessionRequest = new UpdatePaymentSessionRequest(); // UpdatePaymentSessionRequest | 
    try {
      apiInstance.updateCustomerPaymentSession(xWalletID, paymentSessionId, updatePaymentSessionRequest);
    } catch (ApiException e) {
      System.err.println("Exception when calling CustomerApi#updateCustomerPaymentSession");
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
 **xWalletID** | **String**|  |
 **paymentSessionId** | **String**| The ID of the specific payment session to retrieve |
 **updatePaymentSessionRequest** | [**UpdatePaymentSessionRequest**](UpdatePaymentSessionRequest.md)|  |

### Return type

null (empty response body)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth), [bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**204** | The payment session has been successfully updated. No content returned. |  -  |

