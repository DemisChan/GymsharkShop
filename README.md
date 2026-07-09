# Gymshark Shop

A small Android app that fetches a product catalogue from a remote JSON endpoint and
presents it as a browsable product list with a detail screen. Built with Jetpack Compose
and an MVVM architecture.

## Features

- Fetches and parses the product payload from the Gymshark mock endpoint.
- Product list with image, title, price, colour and label indicators.
- Graceful handling of missing or broken product images (fallback tile).
- Product labels (e.g. *going fast*, *new*, *recycled nylon*) rendered as badges.
- Detail screen with the full image, price, colour, labels and the product
  description rendered from HTML (not shown as raw tags).
- Loading, empty and error states, with retry on failure.

## Screenshots

|                          Product list                          |                          Product detail                          |                          Splash                           |
|:--------------------------------------------------------------:|:----------------------------------------------------------------:|:---------------------------------------------------------:|
|[Product list](docs/screenshots/Screenshot_20260709_152531.png) |[Product detail](docs/screenshots/Screenshot_20260709_152648.png) | [Splash](docs/screenshots/Screenshot_20260709_152433.png) |



## Architecture

Single module, organised by layer:

```
com.demis.gymsharkshop
├── data          # DTOs, Retrofit API, mapper, repository implementation
├── domain        # Product model, ProductLabel, repository interface, result type
├── di            # Hilt modules (network, repository)
└── presentation  # Compose UI, ViewModels, UI state, navigation, theme
```

- **MVVM** — each screen has a `ViewModel` exposing a single `StateFlow<UiState>`.
  Screens are split into a stateful `*Screen` (hoists the ViewModel) and a stateless,
  previewable `*Content`.
- **Data layer** — Retrofit + kotlinx.serialization; the repository returns a typed
  result and caches the fetched list in memory so the detail screen resolves by id
  without a second network call.
- **DI** — Hilt for wiring the network stack, repository and ViewModels.
- **Images** — Coil for async loading with placeholder and error states.
- **Navigation** — type-safe Navigation Compose (list → detail).

## Tech stack

- Kotlin, Jetpack Compose (Material 3)
- MVVM, Coroutines / Flow
- Hilt (dependency injection)
- Retrofit + kotlinx.serialization (networking)
- Coil (image loading)
- JUnit 4 + MockK (unit testing)

## Requirements

- Android Studio (latest stable)
- Android SDK — `minSdk 24`, `targetSdk 36`, `compileSdk 37`
- A device or emulator with internet access

## Getting started

```bash
git clone <repository-url>
cd GymsharkShop
```

Open the project in Android Studio and let Gradle sync, then run the `app`
configuration on a device or emulator.

From the command line:

```bash
# Build the debug APK
./gradlew :app:assembleDebug

# Run the unit tests
./gradlew :app:testDebugUnitTest
```

## API

The app reads from:

```
https://cdn.develop.gymshark.com/training/mock-product-responses/algolia-example-payload.json
```