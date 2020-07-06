# CustomerApi

All URIs are relative to *http://localhost:3000*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getCustomerPaymentDetailsByPaymentId**](CustomerApi.md#getCustomerPaymentDetailsByPaymentId) | **GET** /customer/payments/{paymentRequestId} | Get Payment Details
[**getCustomerPaymentDetailsByQRCodeId**](CustomerApi.md#getCustomerPaymentDetailsByQRCodeId) | **GET** /customer/qr/{qrId} | Get Payment From QR
[**getCustomerPaymentInstruments**](CustomerApi.md#getCustomerPaymentInstruments) | **GET** /customer/instruments | Get Payment Instruments
[**getCustomerPreferences**](CustomerApi.md#getCustomerPreferences) | **GET** /customer/preferences | Get Preferences
[**getCustomerTransactionDetails**](CustomerApi.md#getCustomerTransactionDetails) | **GET** /customer/transactions/{transactionId} | Get Transaction Details
[**getCustomerTransactions**](CustomerApi.md#getCustomerTransactions) | **GET** /customer/transactions | Get Transaction List
[**initiatePaymentInstrumentAddition**](CustomerApi.md#initiatePaymentInstrumentAddition) | **POST** /customer/instruments | Initiate Instrument Addition
[**makeCustomerPayment**](CustomerApi.md#makeCustomerPayment) | **PUT** /customer/payments/{paymentRequestId} | Pay Payment
[**setCustomerPreferences**](CustomerApi.md#setCustomerPreferences) | **POST** /customer/preferences | Set Preferences


<a name="getCustomerPaymentDetailsByPaymentId"></a>
# **getCustomerPaymentDetailsByPaymentId**
> GetCustomerPaymentResult getCustomerPaymentDetailsByPaymentId(paymentRequestId)

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
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    CustomerApi apiInstance = new CustomerApi(defaultClient);
    String paymentRequestId = "paymentRequestId_example"; // String | The ID of the specific payment request
    try {
      GetCustomerPaymentResult result = apiInstance.getCustomerPaymentDetailsByPaymentId(paymentRequestId);
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
 **paymentRequestId** | **String**| The ID of the specific payment request |

### Return type

[**GetCustomerPaymentResult**](GetCustomerPaymentResult.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |
**400** | The specified Payment Request ID doesn&#39;t exist, has been used or is expired |  -  |

<a name="getCustomerPaymentDetailsByQRCodeId"></a>
# **getCustomerPaymentDetailsByQRCodeId**
> GetCustomerPaymentResult getCustomerPaymentDetailsByQRCodeId(qrId)

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
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    CustomerApi apiInstance = new CustomerApi(defaultClient);
    String qrId = "qrId_example"; // String | The ID of the specific QR Code
    try {
      GetCustomerPaymentResult result = apiInstance.getCustomerPaymentDetailsByQRCodeId(qrId);
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
 **qrId** | **String**| The ID of the specific QR Code |

### Return type

[**GetCustomerPaymentResult**](GetCustomerPaymentResult.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |
**400** | The specified QR Code ID doesn&#39;t exist or has been expired or the underlying payment request is no longer usable |  -  |

<a name="getCustomerPaymentInstruments"></a>
# **getCustomerPaymentInstruments**
> GetCustomerPaymentInstrumentsResults getCustomerPaymentInstruments()

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
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    CustomerApi apiInstance = new CustomerApi(defaultClient);
    try {
      GetCustomerPaymentInstrumentsResults result = apiInstance.getCustomerPaymentInstruments();
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
This endpoint does not need any parameter.

### Return type

[**GetCustomerPaymentInstrumentsResults**](GetCustomerPaymentInstrumentsResults.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |

<a name="getCustomerPreferences"></a>
# **getCustomerPreferences**
> CustomerPreferencesResult getCustomerPreferences()

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
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    CustomerApi apiInstance = new CustomerApi(defaultClient);
    try {
      CustomerPreferencesResult result = apiInstance.getCustomerPreferences();
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

<a name="getCustomerTransactionDetails"></a>
# **getCustomerTransactionDetails**
> GetCustomerTransactionDetailsResults getCustomerTransactionDetails(transactionId)

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
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    CustomerApi apiInstance = new CustomerApi(defaultClient);
    String transactionId = 75ba5b0b-7e5d-47fe-9508-29ca69fdb1d5; // String | The ID of the specific transaction
    try {
      GetCustomerTransactionDetailsResults result = apiInstance.getCustomerTransactionDetails(transactionId);
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
 **transactionId** | **String**| The ID of the specific transaction |

### Return type

[**GetCustomerTransactionDetailsResults**](GetCustomerTransactionDetailsResults.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |

<a name="getCustomerTransactions"></a>
# **getCustomerTransactions**
> GetCustomerTransactionsResult getCustomerTransactions(paymentRequestId, startTime, endTime, pageSize, page)

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
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    CustomerApi apiInstance = new CustomerApi(defaultClient);
    String paymentRequestId = f27b0189-3449-4215-ab95-31c24e775a48; // String | If present, limits the list of transactions to those that relate to the payment request
    OffsetDateTime startTime = 2017-11-06T19:38:09.890+11:00; // OffsetDateTime | If present, the date/time to limit transactions returned.  Transactions older than this time will not be returned
    OffsetDateTime endTime = 2017-11-06T19:38:09.890+11:00; // OffsetDateTime | If present, the date/time to limit transactions returned.  Transactions newer than this time will not be returned
    Integer pageSize = 25; // Integer | The number of records to return for this page.  Defaults to 25 if absent
    Integer page = 1; // Integer | The page of results to return with 1 indicating the first page.  Defaults to 1 if absent
    try {
      GetCustomerTransactionsResult result = apiInstance.getCustomerTransactions(paymentRequestId, startTime, endTime, pageSize, page);
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
 **paymentRequestId** | **String**| If present, limits the list of transactions to those that relate to the payment request | [optional]
 **startTime** | **OffsetDateTime**| If present, the date/time to limit transactions returned.  Transactions older than this time will not be returned | [optional]
 **endTime** | **OffsetDateTime**| If present, the date/time to limit transactions returned.  Transactions newer than this time will not be returned | [optional]
 **pageSize** | **Integer**| The number of records to return for this page.  Defaults to 25 if absent | [optional] [default to 25]
 **page** | **Integer**| The page of results to return with 1 indicating the first page.  Defaults to 1 if absent | [optional] [default to 1]

### Return type

[**GetCustomerTransactionsResult**](GetCustomerTransactionsResult.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |

<a name="initiatePaymentInstrumentAddition"></a>
# **initiatePaymentInstrumentAddition**
> InitiatePaymentInstrumentAdditionResults initiatePaymentInstrumentAddition(instrumentAdditionDetails)

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
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    CustomerApi apiInstance = new CustomerApi(defaultClient);
    InstrumentAdditionDetails instrumentAdditionDetails = new InstrumentAdditionDetails(); // InstrumentAdditionDetails | 
    try {
      InitiatePaymentInstrumentAdditionResults result = apiInstance.initiatePaymentInstrumentAddition(instrumentAdditionDetails);
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
 **instrumentAdditionDetails** | [**InstrumentAdditionDetails**](InstrumentAdditionDetails.md)|  |

### Return type

[**InitiatePaymentInstrumentAdditionResults**](InitiatePaymentInstrumentAdditionResults.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |

<a name="makeCustomerPayment"></a>
# **makeCustomerPayment**
> MakeCustomerPaymentResults makeCustomerPayment(paymentRequestId, customerPaymentDetails)

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
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    CustomerApi apiInstance = new CustomerApi(defaultClient);
    String paymentRequestId = "paymentRequestId_example"; // String | The ID of the specific payment request
    CustomerPaymentDetails customerPaymentDetails = new CustomerPaymentDetails(); // CustomerPaymentDetails | 
    try {
      MakeCustomerPaymentResults result = apiInstance.makeCustomerPayment(paymentRequestId, customerPaymentDetails);
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
 **paymentRequestId** | **String**| The ID of the specific payment request |
 **customerPaymentDetails** | [**CustomerPaymentDetails**](CustomerPaymentDetails.md)|  |

### Return type

[**MakeCustomerPaymentResults**](MakeCustomerPaymentResults.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |

<a name="setCustomerPreferences"></a>
# **setCustomerPreferences**
> setCustomerPreferences(customerPreferences)

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
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    CustomerApi apiInstance = new CustomerApi(defaultClient);
    CustomerPreferences customerPreferences = new CustomerPreferences(); // CustomerPreferences | 
    try {
      apiInstance.setCustomerPreferences(customerPreferences);
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
 **customerPreferences** | [**CustomerPreferences**](CustomerPreferences.md)|  |

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

