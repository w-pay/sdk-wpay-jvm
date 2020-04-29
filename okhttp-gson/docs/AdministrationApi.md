# AdministrationApi

All URIs are relative to *http://localhost:3000*

Method | HTTP request | Description
------------- | ------------- | -------------
[**checkHealth**](AdministrationApi.md#checkHealth) | **GET** / | Health Check


<a name="checkHealth"></a>
# **checkHealth**
> HealthCheckResult checkHealth()

Health Check

Perform a health check on the Village Wallet implementation

### Example
```java
// Import classes:
import au.com.woolworths.village.sdk.client.ApiClient;
import au.com.woolworths.village.sdk.client.ApiException;
import au.com.woolworths.village.sdk.client.Configuration;
import au.com.woolworths.village.sdk.client.models.*;
import au.com.woolworths.village.sdk.api.AdministrationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:3000");

    AdministrationApi apiInstance = new AdministrationApi(defaultClient);
    try {
      HealthCheckResult result = apiInstance.checkHealth();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AdministrationApi#checkHealth");
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

[**HealthCheckResult**](HealthCheckResult.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |

