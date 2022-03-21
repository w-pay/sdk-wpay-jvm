# Developing the WPay SDK

## Open Source SDK

The SDK is an Open Source project. While the majority of developer contributions will be coming from developers working
on a proprietary client application, the SDK is open source. This means that changes to the SDK need to be tracked as
issues on the repo. Links in issues/commit messages to closed issue tracking systems (eg: Jira, Trello, etc) are not
to be used as it breaks traceability as to why changes were made to the SDK. There's nothing stopping GitHub issues being
referenced in another issue tracking system for visibility to teams/managers, however it's a unidirectional relationship.

## New versions of the API

As new versions of the [WPay API spec](https://github.com/w-pay/oas-wpay) is released, the SDK will need to be updated.
A version of the SDK targets a specific version of the API spec, in the spec's entirety. That means to uplift the SDK,
all the changes to the API spec need to be incorporated in the new version of the SDK. This ensures that client
applications aren't getting a "piece meal" SDK where some features are missing.

If the SDK is outdated, an "Uplift" issue should be raised.

## Development philosophy

The goal of the SDK is to be light on client applications (in terms of dependencies) while being easy to change the
SDK.

To try and meet these goals the [api-sdk-creator](https://github.com/RedCrewOS/api-sdk-creator-mpp) library is used.
The consequence of that is that a lot of the SDK is written in a Functional Programming style which may be initially
confusing to new developers. However, the purpose of the SDK can easily be modelled as a pipeline of steps.

1. Take some data and convert to an HTTP request.
2. Send the request to the API server
3. Convert the response to a data structure for applications.

Of course, at any step errors might occur and [Monads](https://arrow-kt.io/docs/patterns/monads/) are excellent tools for [handling errors](https://arrow-kt.io/docs/patterns/error_handling/), and changing [the "track"](https://fsharpforfunandprofit.com/rop/) of program execution.

The main differences new developers will likely see in the SDK compared to other imperative/OO programs are:

1. [First class functions](https://mostly-adequate.gitbook.io/mostly-adequate-guide/ch02) and passing functions as arguments to functions.
2. [Currying/partial application](https://mostly-adequate.gitbook.io/mostly-adequate-guide/ch04) of functions.
3. [Function composition](https://mostly-adequate.gitbook.io/mostly-adequate-guide/ch05)
4. [Monads](https://mostly-adequate.gitbook.io/mostly-adequate-guide/ch09)

The core logic of the SDK won't change between API spec versions. What will change is the modification of API
operations (eg: "make a payment") to facilitate consumers of the SDK using the API functionality. Therefore, new
developers can still contribute to "uplift" issues. However, learning the strengths of the FP paradigm is a worthwhile
investment and is recommended.

### Learning Resources

The best place to start to understand the concepts of the SDK, is to start to read the [original motivation](https://github.com/RedCrewOS/api-sdk-creator-js/blob/main/http-api-client/docs/motivation.md) document,
for the ApiSdkCreator project, and the [Kotlin specific](https://github.com/RedCrewOS/api-sdk-creator-mpp/blob/main/http-api-client/README.md) README. 
The [WPay Android Reference Application](https://github.com/w-pay/sdk-reference-android) shows the SDK in action, and can
help guide users of the SDK, as well as developers wanting to update the SDK.

Additional [FP resources](https://github.com/RedCrewOS/api-sdk-creator-js/blob/main/http-api-client/docs/motivation.md#functional-programming-references) 
are available to help explain the concepts that the SDK uses in its implementation.
