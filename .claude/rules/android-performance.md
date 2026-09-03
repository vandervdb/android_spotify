## Android/Compose Performance (objectif long terme)

> **Statut** : ces règles décrivent l'état cible, pas une obligation immédiate.
> Quand Claude modifie ou crée un fichier touchant l'un de ces points, il ne doit **pas**
> appliquer le changement directement. Il doit :
>
> 1. continuer à coder selon l'existant (ne pas casser ce qui fonctionne),
> 2. signaler en fin de réponse les écarts détectés vis-à-vis des règles ci-dessous,
>    sous forme de liste courte "Optimisations possibles (non appliquées)",
> 3. attendre une décision explicite d'Arnaud avant de modifier quoi que ce soit
>    sur ces points (recomposition, threading, images, listes).
>    Ne pas refactorer en passant un fichier existant pour le mettre en conformité
>    avec ces règles sans demande explicite.

- **Recomposition** : état `@Immutable`/`@Stable` (data class avec types stables) pour tout état exposé par un `ViewModel` (`PlayerState`, `SessionState`, etc.). Pas de lambda recréée à chaque recomposition sans `remember` si elle est passée en profondeur dans l'arbre.
- **Collecte de state** : `collectAsStateWithLifecycle()` plutôt que `collectAsState()` pour éviter les collectes hors cycle de vie visible.
- **Listes** : `LazyColumn`/`LazyRow` avec `key = { it.id }` sur `items(...)` — jamais d'index brut comme clé si la liste peut être réordonnée/filtrée.
- **State hoisting** : ne pas hisser un état plus haut que nécessaire — un état modifié à haute fréquence dans un sous-arbre (ex. position de lecture `MiniPlayer`) ne doit pas forcer la recomposition du parent (`SpotifyScreen`).
- **Threading** : tout appel réseau/disque (Ktor, DataStore) sur `Dispatchers.IO`, jamais sur le dispatcher par défaut. `viewModelScope` pour le cycle de vie des coroutines — jamais `GlobalScope`.
- **Images** : Coil avec `size`/`crossfade` explicites pour les pochettes d'album — pas de bitmap plein format pour une vignette de playlist.
- **Hilt** : pas de travail lourd (I/O, calcul) dans un constructeur injecté ou un `init {}` — différer via coroutine/lazy.
- **Preview** : `@Preview` doit utiliser les modules `-fake` (`spotify_fake`), jamais une implémentation réelle avec appel réseau (`SpotifyAppRemote`, Ktor).
