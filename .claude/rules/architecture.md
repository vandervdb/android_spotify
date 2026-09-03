# Architecture Rules

**Rule statuses** — every section below is tagged:
- **[Enforced]** — applies to all code you write or touch, right now.
- **[Target — new code]** — mandatory for new screens/features; existing code does NOT follow it yet. Never rewrite existing code to comply unless that refactor IS the task.

---

## Module map [Enforced]

| Module | Role | Depends on |
|---|---|---|
| `app` | Compose application, navigation, Hilt entry points | `core_ui`, `spotifyclient`, `spotify_fake` |
| `core_ui` | Shared design system (`theme/`: `AndroidAppTheme`, colors, typography) + cross-module contracts (e.g. `IMiniPlayerViewModel`) | `spotifyclient` (for domain state types referenced by shared contracts) |
| `spotifyclient` | Real Spotify integration: data/domain/network layers, Hilt DI (`di/`) | *(none)* |
| `spotify_fake` | Fake `IMiniPlayerViewModel` implementation for previews/dev builds | `spotifyclient`, `core_ui` |

**Dependency direction**: `app` → feature modules → `core_ui`, **except** that `core_ui`'s cross-module contracts (`IMiniPlayerViewModel`) reference domain state types from `spotifyclient`, so `core_ui` depends on `spotifyclient` for those types only. Do not add a *new* reverse dependency beyond this existing one without flagging it — if a task seems to need `core_ui` to depend on `app`, or `spotifyclient` to depend on `core_ui`/`app`, stop and flag it instead of adding it.

---

## Legacy code & known tech debt [Enforced]

Known debt — assume it, don't be surprised by it, don't silently fix it:
- UI strings are largely hardcoded inline in Composables (including French/emoji) rather than via `strings.xml` — only a handful of screens have been migrated so far (`HomeScreen`, `SpotifyScreen`, `SpotifyScreenWrapper`). Do not silently migrate the rest as a side-effect of an unrelated task; report it instead

**How to behave in legacy/debt areas:**
1. **Boy-scout, scoped**: when your task touches debt-laden code, improve only the part you modify. Never refactor a whole file "while you're there"
2. **Don't widen scope**: a refactor beyond the task at hand must be proposed, not done. Report what you found; let the user decide
3. **Never make debt worse**: no new hardcoded module wiring outside `settings.gradle.kts` conventions, no new cross-module dependency beyond the one documented above, no new dead modules

---

## MVVM pattern [Target — new code, matches most existing code]

| Role | Rules |
|---|---|
| **Model / domain** | Plain Kotlin — data classes, use cases, repository interfaces in `domain/`. Zero Android/Compose dependency |
| **ViewModel** | `@HiltViewModel`, exposes state as `StateFlow` (see `kotlin.md`), no Compose imports, no `Context` unless via `@ApplicationContext` for a genuine platform need |
| **Composable (View)** | Reads `ViewModel` state, delegates actions back via method calls — no business logic, no direct repository/data-source access |

**Naming:** `<Feature>ViewModel` / `<Feature>Screen` for the top-level stateful composable (matches `SpotifyViewModel` / `SpotifyScreen`).

**State shape**: this codebase currently exposes several typed `StateFlow`s per ViewModel (e.g. `PlayerState`, `SessionState`) rather than one combined `UiState` — follow that existing pattern unless the task specifically asks to consolidate it.

**Forbidden in new MVVM code:**
- Business logic or direct data-source/repository calls in a Composable
- Android/Compose imports in a ViewModel or domain class
- A new screen bypassing the ViewModel to read a repository/use case directly

---

## Dependencies & singletons [Enforced]

- No hand-rolled singletons/service locators — Hilt scopes (see `kotlin.md`) are the only DI mechanism
- Depend on interfaces (`domain/`), not concrete repository/data-source implementations, from any ViewModel or use case
- Cross-module contracts (interfaces meant to be implemented differently in `spotifyclient` vs `spotify_fake`) belong in `core_ui`
