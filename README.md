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

**Caveat:** Due to not being able to [provide the API spec dynamically](https://github.com/OpenAPITools/openapi-generator/issues/5965),
the Village API spec is expected to be found in the root of the project.

A symlink will suffice.

## Building an SDK variant

The project takes advantage of Gradle [Multi Project Builds](https://docs.gradle.org/current/userguide/multi_project_builds.html)
to allow configuration and execution of the tasks to build/test/publish
an SDK variant.

As such, the top level tasks of `build`, `test`, etc will run these tasks
**for all** SDK variants. To run (address) a specific variant, use the
path to the project (see [Gradle docs](https://docs.gradle.org/current/userguide/multi_project_builds.html#sec:running_partial_build_from_the_root)
for more details).

For example, to build only the SDK using "OkHTTP with GSON" run

```sh
$ ./gradlew :okhttp-gson:build
```

## Testing and SDK variant

In order to see how dependencies are applied run

```sh
$ ./gradlew projectReport 
```

TODO: Testing

## Publishing a version

TODO: Publishing
TODO: Version schemes, tagging

## Adding a new variant

1. Add the Open API generator library name to the root `libraries` array. A
new task will be added to the "SDK Generators" task group.
2. Run the generation task
3. Add the project to the `settings.gradle` so that the top level build
can "load" the project
4. Run tasks to build, test, etc
5. Commit the new SDK variant.

