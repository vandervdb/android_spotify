# Kotlin Rules (mandatory — all code)

## Conventions

- **English** identifiers everywhere — no French names
- **Comment language**: match the language already used in the file's comments; if the file has none (or they are mixed), write them in English. When touching a file with mixed-language comments, rewrite the comments of the lines you touch in English
- **Package root**: `org.vander.<module>` (e.g. `org.vander.spotifyclient`, `org.vander.androidapp`)
- **`val`** over `var` everywhere — a `var` must be justified by actual mutation, not convenience
- **Null-safety**: `?.`, `?:`, `let`/`takeIf` over explicit null checks. `!!` is forbidden except where nullability is provably impossible (and then add a one-line comment why). Prefer `sealed class`/`kotlin.Result` for error states over nullable return types that conflate "absent" and "failed"
- **Data classes** for all models, DTOs and UI/domain state — never a plain class with public mutable fields
- **Pattern matching**: prefer `when` with exhaustive `sealed class`/`sealed interface` branches over `if/else if` chains on type or state
- **Guard clauses**: early return over nested `if` blocks — validate preconditions at top, return immediately

```kotlin
// ❌
fun process(item: Item?) {
    if (item != null) {
        if (item.isValid) {
            doSomething(item)
        }
    }
}

// ✅
fun process(item: Item?) {
    if (item == null) return
    if (!item.isValid) return
    doSomething(item)
}
```

---

## Coroutines & Flow

- **Scope**: `viewModelScope` in ViewModels, `lifecycleScope`/`repeatOnLifecycle` in UI — never `GlobalScope`
- **Dispatchers**: `Dispatchers.IO` for network/disk, default (Main) for UI-touching code — never block Main with I/O
- **State exposure**: `private val _x = MutableStateFlow(...)` + `val x = _x.asStateFlow()` — never expose a mutable `StateFlow`/`MutableSharedFlow` publicly
- **Cancellation**: a `Job`/`CoroutineScope` created outside a lifecycle-scoped builder must be cancelled explicitly when no longer needed
- **Suspend functions**: no `runBlocking` outside tests and app entry points

---

## Dependency Injection (Hilt)

- One `@Module` per concern, placed in the owning module's `di/` package (e.g. `NetworkModule`, `RepositoryModule`, `AuthModule`) — never one giant module
- Bind interfaces to implementations with `@Binds` on an `abstract class`; use `@Provides` only for objects Hilt can't construct directly (SDK clients, `DataStore`, Ktor `HttpClient`, ...)
- Pick the narrowest `@InstallIn` scope that fits (`ViewModelComponent` + `@ViewModelScoped` for use-case/repository instances tied to a screen, `SingletonComponent` for app-wide singletons) — do not default everything to `SingletonComponent`
- ViewModels: `@HiltViewModel` + `@Inject constructor(...)` — no manual `ViewModelProvider.Factory` unless Hilt genuinely can't cover the case

---

## Error handling & logging

- Wrap external parsing/deserialization and remote calls in `runCatching`/`try-catch`, return `kotlin.Result<T>` or a domain sealed type — never let an exception escape a repository/data-source boundary silently
- **Logging**: `android.util.Log` with a `private const val TAG = "ClassName"` per class, e.g. `Log.e(TAG, "reason", throwable)`. Every error path logs before returning or surfacing a failure — never fail silently
- Log the class tag as prefix (already covered by `TAG`) to allow filtering in Logcat

---

## Testing conventions used in this repo

- **Mocking**: MockK (`mockk`, `every`, `coEvery`) — not Mockito
- **Flow testing**: Turbine (`.test { awaitItem() ... }`)
- **Coroutine testing**: `kotlinx-coroutines-test` (`runTest`, `StandardTestDispatcher`)
- See `.claude/rules/testing.md` for organisation and test-first workflow
