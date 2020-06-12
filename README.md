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
 - `objc` - Generates a SDK for use in an XCode project.

Each variant's README contains more details on how to use the SDK, however
the variants follow a naming pattern so that it's easy to swap variants
if required.

## Versioning

The SDK follows the same versioning scheme as the Village API spec.
So if the API is at `x.y.z` the SDK will be version `x.y.z.b` where `b`
is the build number. The build number allows for implementation changes
to be versioned that don't require a version bump to the spec because the
interface hasn't changed.

## Generating the SDK

If the SDK for a variant needs to be updated the relevant "SDK Generators"
task can be used; see `$ ./gradlew tasks` for details.

**Caveat:** Due to not being able to [provide the API spec dynamically](https://github.com/OpenAPITools/openapi-generator/issues/5965),
the Village API spec is expected to be found in the root of the project.

A symlink will suffice.

## Building an SDK variant

### Gradle projects

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

### XCode

Using the ObjC variant requires the use of [CocoaPods](https://cocoapods.org/)
as a Pod Spec is generated to include the library in an XCode project.

See the `objc` README for more information about how to integrate the
library.

## Testing an SDK variant

**NOTE:** Tests currently are only available for the Java based SDK
variants.

For each SDK variant we want to create a "virtual test project", that
mixes the source of the SDK library, the Android tools, and the tests
for the SDK. By organising by a "virtual project" we can keep
classpaths separate and compiled code from mixing between SDK variants.
In stock Gradle this could be achieved through `sourceSets`,
`configurations` and `dependencies` configuration.

However we can't [mix](https://stackoverflow.com/questions/49714744/failed-to-resolve-project-android-library-and-java-library-module-dependency)
Android [modules](https://docs.gradle.org/current/userguide/dependency_management_terminology.html#sub:terminology_module)
(projects) into Java modules (projects), which prevents us from allowing
each SDK variant the ability to manage it's build/test lifecycle with
the Android libraries and the SDK tests being mixed into the variant
project (to help with isolation).

Unfortunately the Android Gradle Plugin doesn't not allow arbitrary
`sourceSets`, etc to be configured by the root project as it provides
it's own `sourceSet` implementation in order to implement features
(like flavour variants). Consequently we utilise the [Product Flavour](https://developer.android.com/studio/build/build-variants#product-flavors)
facility to dynamically create a product flavour per SDK variant to
then allow the isolated testing of each SDK variant. This means that
unit tests are run in isolation, and if Android Instrumentation Tests
want to be run, a separate APK per SDK variant will be produced; again
for isolation.

### Dependency details

Each flavour variant gets its own dependency tree and classpath. To help
see what dependencies are being used a project report is available.

```sh
$ ./gradlew projectReport 
```

### Testing setup

The SDK tests make HTTP requests to a stub server to validate endpoint
IO. The tests are concerned about data structures only. The SDK doesn't
care that an ID is a guid or not, it cares that a string is sent or received.

Therefore the tests are not reliant on particular data values.

Any server can be used for the SDK tests, however a default `api-stubs`
is available. It uses a [Docker Imposter](https://github.com/outofcoffee/imposter)
to return data provided by `examples` in the API spec. Therefore the
`examples` should be kept up to date.

A [Docker Compose](https://docs.docker.com/compose/) file is provided to
run the stubs on `http://localhost:8080`

**Caveat**: If using Docker, a symlink to the spec won't work because
Docker wont be able to resolve the destination of the link from inside
the container. The file has to be present in the `api-stubs` dir

```shell
$ cd api-stubs
$ cp `readlink ../village.json` .
$ docker-compose up --force-recreate
```

### Running tests

The SDK tests need to be run against and SDK variant, and the [Gradle
build](https://developer.android.com/studio/test/command-line) is setup
to do that. To run the tests for a particular variant run the right
task ie: `test[SDK Variant]DebugUnitTest` which follows the Android SDK
task naming conventions.

To run a specific test, use the `--test` [feature](https://docs.gradle.org/current/userguide/java_testing.html#test_filtering)
of Gradle to specify the FQN of the test you want to run.

#### Using Android Studio (IDE)

In order to run tests in an IDE ie: Android Studio, the default
`Android JUnit` [Run Configuration](https://developer.android.com/studio/test#run_a_test)
can't be used as the IDE will not be able to configure the classpath
correctly and the SDK variant source will not be found. The project
will either fail to compile or throw a `ClassNotFoundException` at runtime.

The IDE `Gradle` [Run Configuration](https://www.jetbrains.com/help/idea/create-run-debug-configuration-gradle-tasks.html)
should be used with the `--test` option in the `Arguments`. This will run
the task allowing the test to be run in the IDE.

## Publishing a version

- TODO: Publishing
- TODO: Tagging

## Adding a new variant

1. Add the Open API generator details to the root `sdks` dictionary. A
new task will be added to the "SDK Generators" task group.
2. Run the generation task
3. For Java/Gradle projects, add the project to the `settings.gradle`
so that the top level build can "load" the project
4. Run tasks to build, test, etc
5. Commit the new SDK variant.

