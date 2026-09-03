⚠️ RAPPEL POST-COMPACTION — règles non négociables
- Kotlin : `val` partout où possible, null-safety stricte (pas de `!!` sauf impossibilité prouvée), guard clauses
- Coroutines : `viewModelScope`/`lifecycleScope` uniquement — jamais `GlobalScope`
- DI : Hilt — un `@Module` par domaine dans `di/`, jamais de service locator maison
- Tests : toute nouvelle classe/logique → test unitaire correspondant dans `src/test` du module (JUnit + MockK + Turbine)
- Modules : `app` dépend de `core_ui`/`spotifyclient`/`spotify_fake` — jamais l'inverse (sauf `core_ui`→`spotifyclient` déjà existant pour les types de state partagés) ; voir `.claude/rules/architecture.md`
- Dette connue : la plupart des écrans ont encore des strings en dur dans les Composables (`strings.xml` seulement partiellement adopté) — ne pas corriger silencieusement, voir `architecture.md`
