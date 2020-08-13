# Woolworths Village Wallet Android SDK

This project contains an Android library that can facilitate
applications accessing the Village API.

The SDK is currently in development. Therefore parts may change.

## Usage

The SDK is modelled after popular "API SDKs" like Stripe. It has the
following core design philosophies.

1. Technology agnostic. Different applications may different technology
choices and an SDK shouldn't force an application to depend on a different
technology stack as this bloats the build and increases complexity.

2. Swappable. Don't like a particular implementation of a part in the
SDK, then swap it out for another object that implements the correct
interface.

The SDK comprises of:
 - An adaption layer between the Application and the API
 - An API layer which knows how to communicate with the Village API
 - An authentication layer.

Applications have the flexibility to plug in different implementations of
the interfaces to allow particular technology choices (eg: choice of
HTTP client library). This makes it very easy to use the SDK in an
existing project, without necessarily introducing extra dependencies.

The entry point for applications is the `CustomerVillage` class or
`MerchantVillage` class depending on the goals of the application.

### Authentication layer

In order to access protected APIs, the SDK will need to know how to
authenticate with the API or a gateway that protects the API. The
`ApiAuthenticator` interface abstracts how the SDK authenticates from
the rest of the API interface. Applications that have a preexisting
authentication workflow can either update the relevant classes to implement the
`ApiAuthenticator` interface, or provide an [Adapter](https://en.wikipedia.org/wiki/Adapter_pattern#Java)
to make the existing authentication details available to the application.

### API layer

The API layer is decoupled from the rest of the SDK via the
`VillageCustomerApiRepository` and `VillageMerchantApiRepository`
interfaces. Consumers need to configure their `Village` instance with
an implementation of the correct repository that conforms to needs and
technology choices of the application.

#### Open API Implementation

For convenience, the [Woolworths Village SDK Open API Repository](https://github.com/woolworthslimited/paysdk2-openapi)
project provides an implementation of the API Repository interfaces
that wraps an API Client created with the Open API generator.

#### Reference Application

A [Reference Application](https://github.com/woolworthslimited/paysdk2-reference-android) is available
to demonstrate the use of the SDK. The library can be imported directly into a project
or Gradle Composite Builds can be used.

## Versioning

The SDK follows [Semantic Versioning](https://semver.org/) principles.
As such if the API specification changes in a way that introduces breaking
changes (eg: path change or data changes) the major version of the SDK
will be increased.

The SDK currently supports version 0.0.5 of the API spec.

- TODO: Publishing