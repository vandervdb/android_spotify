# Règles de rédaction des issues GitHub

## Type

| Situation | Label |
|---|---|
| Nouvelle fonctionnalité ou évolution | `enhancement` |
| Comportement incorrect ou régressif | `bug` |
| Dette technique / nettoyage sans impact fonctionnel | `chore` |

## Titre

- Bug : `[Module] : comportement observé` (ex. `spotifyclient : le token n'est pas rafraîchi après expiration`)
- Enhancement : verbe à l'infinitif décrivant le résultat attendu (ex. `Ajouter la reprise de lecture après reconnexion réseau`)
- Chore : verbe à l'infinitif (ex. `Retirer le module spotifyclient-fake non utilisé`)

`Module` = un des modules Gradle (`app`, `core_ui`, `spotifyclient`, `spotify_fake`) ou `build` pour la config Gradle/CI.

## Corps

**Contexte** *(optionnel, 2-3 lignes si non évident)*
Pourquoi c'est nécessaire — pas comment l'implémenter.

**Comportement observé / attendu** *(bug)* ou **Description** *(enhancement/chore)*
Comportement observable, pas de détail d'implémentation.

**Étapes de reproduction** *(bug, si non triviales)*
Liste ordonnée, minimale.

**Critères d'acceptation** *(enhancement, si ambigu ou multi-cas)*
- Liste à puces, formulés comme des comportements vérifiables

## À ne pas faire

- Pas de nom de méthode/fichier dans le titre
- Pas de sous-tâches automatiques
- Ne pas dupliquer dans le corps ce que le titre dit déjà
- Ne pas mentionner de détail d'implémentation dans les critères d'acceptation
