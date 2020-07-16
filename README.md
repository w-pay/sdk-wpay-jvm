# Woolworths Village Android SDK

This project contains a collection of libraries that form the SDK to
facilitate Android applications accessing the Village API.

The SDK is currently in development. Therefore parts may change.

## Usage

The SDK is modelled after popular "API SDKs" like Stripe. It has the
following core design philosophies.

1. Technology agnostic. Different applications may different technology
choices and an SDK shouldn't force an application to depend on a different
technology stack as this bloats the build and increases complexity.

2. Swappable. Don't like a particular implementation of the SDK, then
swap it out for another object that implements the correct interface.

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

### API layer

The API layer is decoupled from the rest of the SDK via the
`VillageApiRepository` interface. Consumers need to configure their
`Village` instance with an implementation of `VillageApiRepository`

#### Open API implementation

The SDK can be used with an implementation of the `VillageApiRepository`
that uses the [Open API generator](https://openapi-generator.tech/) to
provide an API Client with DTOs representing the data structures of the
API. The SDK can be configured to use different libraries that the Open
API generator supports to again make it easier to embed into an existing
project with existing technology choices.

The use of an Open API implementation is a compile time choice as the
relevant libraries will all need to be included. It is not recommended to
try to use multiple implementations in the one build/app.

The current Open API implementations are:
 - `okhttp-gson` - Uses the [OkHttp](https://square.github.io/okhttp/)
     framework with [GSON](https://github.com/google/gson) for JSON
     (de)serialisation.
 - `objc` - Generates a SDK for use in an XCode project.

Each implementation's README contains more details on how to use the
API client, however the implementations follow a naming pattern so
that it's easy to swap variants if required.

##### Versioning

Open API implementations follow the same versioning scheme as the Village API spec.
So if the API is at `x.y.z` the implementation will be version `x.y.z.b` where `b`
is the build number. The build number allows for implementation changes
to be versioned that don't require a version bump to the spec because the
interface hasn't changed.

#### Generating an Open API implementation

If the implementation needs to be updated the relevant "SDK API Generators"
task can be used; see `$ ./gradlew tasks` for details.

**Caveat:** Due to not being able to [provide the API spec dynamically](https://github.com/OpenAPITools/openapi-generator/issues/5965),
the Village API spec is expected to be found in the root of the project.

A symlink will suffice.

#### Building an Open API implementation

##### Gradle projects

The project takes advantage of Gradle [Multi Project Builds](https://docs.gradle.org/current/userguide/multi_project_builds.html)
to allow configuration and execution of the tasks to build/test/publish
an Open API implementation.

As such, the top level tasks of `build`, `test`, etc will run these tasks
**for all** Open API implementation. To run (address) a specific implementation, use the
path to the project (see [Gradle docs](https://docs.gradle.org/current/userguide/multi_project_builds.html#sec:running_partial_build_from_the_root)
for more details).

For example, to build only the API client using "OkHTTP with GSON" run

```sh
$ ./gradlew :okhttp-gson:build
```

##### XCode

Using the ObjC implementation requires the use of [CocoaPods](https://cocoapods.org/)
as a Pod Spec is generated to include the library in an XCode project.

See the `objc` README for more information about how to integrate the
library.

#### Testing an Open API implementation

**NOTE:** Tests currently are only available for the Java based Open API
implementations.

For each Open API implementation we want to create a "virtual test project", that
mixes the source of the library, the Android tools, and the tests
for the API. By organising by a "virtual project" we can keep
classpaths separate and compiled code from mixing between Open API implementations.
In stock Gradle this could be achieved through `sourceSets`,
`configurations` and `dependencies` configuration.

However we can't [mix](https://stackoverflow.com/questions/49714744/failed-to-resolve-project-android-library-and-java-library-module-dependency)
Android [modules](https://docs.gradle.org/current/userguide/dependency_management_terminology.html#sub:terminology_module)
(projects) into Java modules (projects), which prevents us from allowing
each Open API implementation the ability to manage it's build/test lifecycle with
the Android libraries and the API tests being mixed into the implementation
project (to help with isolation).

Unfortunately the Android Gradle Plugin doesn't not allow arbitrary
`sourceSets`, etc to be configured by the root project as it provides
it's own `sourceSet` implementation in order to implement features
(like flavour variants). Consequently we utilise the [Product Flavour](https://developer.android.com/studio/build/build-variants#product-flavors)
facility to dynamically create a product flavour per Open API implementation to
then allow the isolated testing of each API implementation. This means that
unit tests are run in isolation, and if Android Instrumentation Tests
want to be run, a separate APK per Open API implementation will be produced; again
for isolation.

#### Dependency details

Each flavour variant gets its own dependency tree and classpath. To help
see what dependencies are being used a project report is available.

```sh
$ ./gradlew projectReport 
```

#### Testing setup

The API tests make HTTP requests to a stub server to validate endpoint
IO. The tests are concerned about data structures only. The API client doesn't
care that an ID is a guid or not, it cares that a string is sent or received.

Therefore the tests are not reliant on particular data values.

Any server can be used for the API tests, however a default `api-stubs`
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

#### Running tests

The API tests need to be run against an Open API implementation, and the [Gradle
build](https://developer.android.com/studio/test/command-line) is setup
to do that. To run the tests for a particular variant run the right
task ie: `test[API Implementation]DebugUnitTest` which follows the Android SDK API
task naming conventions.

To run a specific test, use the `--test` [feature](https://docs.gradle.org/current/userguide/java_testing.html#test_filtering)
of Gradle to specify the FQN of the test you want to run.

##### Using Android Studio (IDE)

In order to run tests in an IDE ie: Android Studio, the default
`Android JUnit` [Run Configuration](https://developer.android.com/studio/test#run_a_test)
can't be used as the IDE will not be able to configure the classpath
correctly and the Open API implementation source will not be found. The project
will either fail to compile or throw a `ClassNotFoundException` at runtime.

The IDE `Gradle` [Run Configuration](https://www.jetbrains.com/help/idea/create-run-debug-configuration-gradle-tasks.html)
should be used with the `--tests` option in the `Arguments`. This will run
the task allowing the test to be run in the IDE.

#### Publishing a Open API implementation version

- TODO: Publishing
- TODO: Tagging

#### Adding a new Open API implementation

1. Add the Open API generator details to the root `apis` dictionary. A
new task will be added to the "SDK API Generators" task group.
2. Run the generation task
3. For Java/Gradle projects, add the project to the `settings.gradle`
so that the top level build can "load" the project
4. Run tasks to build, test, etc
5. Commit the new Open API implementation.

