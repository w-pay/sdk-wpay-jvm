# Woolworths Village Android SDK

This project contains libraries for use in Android applications to
access the Village API.

## Usage

The modules correspond to a variant of the SDK that uses a particular
Java HTTP framework to communicate with the API. This allows application
developers to pick the variant that best meets their needs.

The supported variants are:
 - `okhttp-gson` - Uses the [OkHttp](https://square.github.io/okhttp/)
     framework with [GSON](https://github.com/google/gson) for JSON
     (de)serialisation.

Each variant's README contains more details on how to use the SDK, however
the variants follow a naming pattern so that it's easy to swap variants
if required.

## Versioning

The SDK follows the same versioning scheme as the Village API spec.
So if the API is at `x.y.z` the SDK will be version `x.y.z`

## Generating the SDK

If the SDK for a variant needs to be updated the relevant "SDK Generators"
task can be used; see `$ ./gradlew tasks` for details.

**Caveat:** Due to not being able to provide the API spec dynamically, the
Village API spec is expected to be found in the `sdk` module. A symlink
will suffice.

TODO: GH link

TODO: Testing
TODO: Publishing