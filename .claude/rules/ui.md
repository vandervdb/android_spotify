# UI Rules (mandatory)

## Stack

Jetpack Compose + Material3 only — no XML layouts, no View system.

## Composable structure

- Top-level screen composables are named `<Feature>Screen` and read state from a `ViewModel` (see `architecture.md`)
- Reusable, presentation-only pieces (`MiniPlayer`, `MarqueeTextInfinite`, `ScaffoldWithBottomBar`, ...) live alongside/under `presentation/components/` and take data + lambdas as parameters — no ViewModel reference inside a pure component
- **[Target — new code]** New reusable components should be stateless (state hoisted to the caller) even though some existing screens read a ViewModel directly inside the composable — do not retrofit existing screens to this pattern outside the task at hand
- `@Preview` composables use the fake implementations (`spotify_fake`'s `FakeMiniPlayerViewModel`, or static sample data) — never a real network-backed dependency

## Theming

- **[Enforced]** `core_ui` (`org.vander.coreui.theme`) is the shared design system — `AndroidAppTheme`, colors (`Color.kt`) and typography (`Type.kt`) live there. `app` consumes it; never re-add a local theme package under `app/.../ui/theme`
- No hardcoded `Color(0xFF...)` or raw `.dp` magic numbers scattered across a screen — use `MaterialTheme` tokens, or add a new constant to `core_ui`'s theme files if it's meant to be reused

## Strings

- **[Enforced — new code]** New user-facing strings go through `stringResource(R.string.xxx)` in `strings.xml` — this is already the pattern for `HomeScreen`, `SpotifyScreen` and `SpotifyScreenWrapper`
- Most other existing screens still hardcode strings inline (including French text) — do not migrate them as a side-effect of an unrelated task; report it instead if you notice inconsistency in a screen you're already touching
- Never introduce a new hardcoded string in a language different from the file's existing convention without flagging it

## State & recomposition

- Collect `StateFlow` with `collectAsStateWithLifecycle()` (not the plain `collectAsState()`, which ignores lifecycle) in new code
- Pass `data class`/`sealed interface` state down, not individual primitives, when more than 2-3 values move together
- See `.claude/rules/android-performance.md` for recomposition-cost specifics — those are reported, not applied silently
