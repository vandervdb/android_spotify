# Testing Rules (mandatory)

## Organisation

- **Unit tests**: `<module>/src/test/kotlin` — domain logic, mappers, repositories, ViewModels. Must run on the JVM without an Android device/emulator (Robolectric only if genuinely unavoidable)
- **Instrumented tests**: `<module>/src/androidTest` — Compose UI tests, anything requiring a real Android runtime
- New ViewModels, use cases, repositories and mappers must be unit-testable in isolation — inject dependencies (interfaces from `domain/`), never reach for a real Hilt graph or a real `SpotifyAppRemote` in a unit test; use the `-fake`/mock equivalents
- **CI**: run the relevant unit tests locally via `./gradlew :<module>:testDebugUnitTest` before considering a change done; run instrumented tests when the change touches Compose UI or Android runtime behavior

## Test-first (mandatory for bug fixes)

1. Write a **failing test** that reproduces the bug before touching any implementation
2. Verify the test fails for the right reason
3. Fix the implementation
4. Verify the test passes and no existing tests regressed

## Verifiable success criteria

Define before starting any non-trivial implementation:
- What specific test passes?
- What observable behavior confirms correctness on device/emulator?
- What existing tests must still pass?

A feature is not complete until all criteria are explicitly verified.

## Tooling in this repo

- **Assertions/runner**: JUnit4
- **Mocking**: MockK (`mockk`/`coEvery`/`verify`) — see `spotifyclient/src/test/kotlin/.../PlayerStateMapperKtTest.kt` for the existing style (mocking `android.util.Log` statics included)
- **Flow**: Turbine for `StateFlow`/`Flow` assertions
- **Coroutines**: `kotlinx-coroutines-test` (`runTest`)
- **Compose UI tests**: `androidx.compose.ui.test.junit4` + `createComposeRule()`, MockK-android for instrumented mocks
