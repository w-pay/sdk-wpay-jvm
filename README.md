# WPay JVM SDK

This project contains a JVM compatible library that can facilitate applications accessing the WPay API.

| :memo: | The SDK is currently in development. Therefore parts may change. |
|--------|:-----------------------------------------------------------------|

## Usage

The SDK is has the following core design philosophies.

1. Technology agnostic. Different applications may have different technology choices and an SDK shouldn't force an 
application to depend on a different technology stack as this bloats the build and increases complexity.

2. Swappable. Don't like a particular implementation of a part in the SDK, then swap it out for another function that
does the job.

The SDK comprises of:
 - An adaption layer between the Application and the API.
 - An API layer which knows how to communicate with the WPay API
 - An authentication layer.

Applications have the flexibility to plug in different implementations of the interfaces to allow particular technology
choices (eg: choice of HTTP client library). This makes it very easy to use the SDK in an existing project, without 
necessarily introducing extra dependencies.

The entry point for applications is the `createCustomerSDK` or `createMerchantSDK` functions depending on the goals of
the application.

### Authentication layer

In order to access protected APIs, the SDK will need to know how to authenticate with the API. The `ApiAuthenticator` 
interface abstracts how the SDK authenticates from the rest of the API interface. Applications that have a preexisting
authentication workflow can either update the relevant classes to implement the `ApiAuthenticator` interface, or provide
an [Adapter](https://en.wikipedia.org/wiki/Adapter_pattern#Java) to make the existing authentication details available
to the SDK. For simple applications, a `ProvidedTokenAuthenticator` is available

### API layer

The API layer is decoupled from the rest of the SDK via the `WPayCustomerApi` and `WPayMerchantApi` classes.

#### Reference Application

A [Reference Application](https://github.com/w-pay/sdk-reference-android) is available to demonstrate the use of the 
SDK in an Android application. The library can be imported directly into a project or Gradle Composite Builds can be
used. However, the library is a plain JAR and can be used on any JVM platform.

## Versioning

The SDK follows [Semantic Versioning](https://semver.org/) principles. As such if the API specification changes in a
way that introduces breaking changes (eg: path change or data changes) the major version of the SDK will be increased.

The SDK currently supports version 1.0.7 of the API spec.

## Getting started

Read the [📘 SDK reference docs](/sdk/docs/sdk/index.html) for more information on the different types
in the SDK.

### Example usage

The examples use the Open API implementation, however any class conforming to the correct interface
can be used

```kotlin
fun createCustomerWPay(): WPayCustomerApi {
    // read the docs on all the options available
    val options = WPayCustomerOptions(
        apiKey = "<your key here>",
        baseUrl = "http://my.app.host.com/context/root",

        // see the docs on how we can use different token types.
        accessToken = ApiTokenType.StringToken("abc123")
    )

    /*
     * This example uses the `ok-http` `HttpClient` module from
     * https://github.com/RedCrewOS/api-sdk-creator-jvm
     */
    return createCustomerSDK(
        okHttpClient(),
        options,
    )
}
```

The different methods on the SDK can be now used.

### Documentation

The SDK reference docs are generated using [Dokka](https://github.com/Kotlin/dokka)

```shell
$ ./gradlew dokkaHtml
```

## Publishing

Newer versions of the SDK are tagged in GitHub and are published via JitPack

## Developing

See [DEVELOPING.md](./DEVELOPING.md) on how to contribute to this SDK.
