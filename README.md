# Android MVVM with Clean Architecture

## Table of Contents

1. [Architecture](#architecture)
2. [Features](#features)
3. [Third-Party Libraries](#third-party-libraries)
4. [Test Coverage](#module-structure)
5. [Running Unit Tests](#running-unit-tests)

## Architecture

This project follows a clean architecture approach, divided into several modules:

- **Data Layer**: Responsible for data management. It includes both local and remote data sources and the repository
  that handles data operations.
    - **Local**: Manages local database interactions.
        - **Model**: Represents local data models.
        - **DAO**: Data Access Objects for database operations.
        - **Database**: The local database configuration.
    - **Remote**: Manages remote data interactions.
        - **API**: Defines API endpoints and network calls.
        - **Model**: Represents remote data models.
    - **Mapper**: Transforms data between different layers.
    - **Repository**: The single source of truth for data, interacting with both local and remote sources.

- **Domain Layer**: Encapsulates business logic.
    - **Model**: Represents domain-specific models.
    - **UseCase**: Contains use cases for the application, implementing business logic.

- **Presentation Layer**: Handles UI-related logic.
    - **ViewModel**: Manages UI-related data and handles user interactions.
    - **UI**: Comprises the UI components (e.g., activities, fragments).

- **Dependency Injection (DI)**: Configures and provides dependencies throughout the application.

- **MainApplication.kt**: The application class for initializing the application-level components.

## Features

- **Modular Architecture**: Each feature is organized into distinct modules, promoting separation of concerns and
  reusability.
- **Clean Architecture**: Ensures a clear separation of concerns across different layers.
- **MVVM Pattern**: Implements the Model-View-ViewModel pattern to manage UI-related data.
- **Dependency Injection**: Utilizes a DI framework to manage dependencies efficiently.
- **Local and Remote Data Handling**: Integrates local databases and remote APIs for data operations.

### Third-Party Libraries

This project utilizes the following third-party libraries:

* Kotlin: For language features and coroutines
* AndroidX: For app compatibility and material design
* Retrofit: For network API calls
* OkHttp: For HTTP client and logging
* MockK: For unit testing and mocking
* JUnit: For unit testing
* Koin: For dependency injection
* Gson: For JSON serialization and deserialization
* Navigation: For navigation components
* Timber: For logging
* Coil: For image loading
* Room: For local database management
* TestNG: For unit testing
* Jupiter: For unit testing
* Slf4j: For logging
* Espresso: For UI testing

### Test Coverage

This project aims to achieve 100% test coverage for all modules. Currently, the test coverage is 100%. I am continuously working to improve the test coverage to ensure the reliability and stability of the application.

## Running Unit Tests

To run unit tests for all modules, use the following command:

```bash
./gradlew testAllModules
```
