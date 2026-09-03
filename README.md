# 🎧 android-compose-spotify

<p align="center">
  <strong>Spotify app built with Jetpack Compose and a modular architecture</strong><br />
  <em>Scalable, strongly typed, production‑ready.</em>
</p>

<p align="center">
  <img alt="tech" src="https://img.shields.io/badge/tech-jetpack_compose-blue?style=flat-square" />
  <img alt="hilt" src="https://img.shields.io/badge/DI-hilt-blueviolet?style=flat-square" />
  <img alt="license" src="https://img.shields.io/badge/license-MIT-green?style=flat-square" />
</p>

---

## 🚀 Overview

Mobile application using a modern Android stack.
The app uses the Spotify API for sign‑in and to control the audio player.

Android project built with:

- 🖌️ **Jetpack Compose**
- 🗄️ **DataStore** for local storage
- 🔌 **Spotify Remote SDK**
- 🧩 **Hilt** for dependency injection
- 🔄 **Ktor** for networking
- 🧱 Multi‑module setup (`core_ui`, `spotifyclient`, etc.)

---

## 📁 Project structure

```bash
AndroidApp/
├── app/                    # Main Compose application
├── core_ui/                # Shared design system (theme) and MiniPlayer contracts
├── spotifyclient/          # Spotify client (auth + remote control)
├── spotify_fake/           # Fake MiniPlayer ViewModel and data for the UI
```

---

## 🛠️ Build & run

```bash
# Build the app
./gradlew assembleDebug

# Install to a connected emulator/device
./gradlew installDebug
```

---

## ⚙️ Secrets configuration

Create a `local.properties` file at the project root with:

```properties
CLIENT_ID=your-spotify-client-id
CLIENT_SECRET=your-spotify-client-secret
```

These values are loaded at build time to generate `BuildConfig` constants used by `spotifyclient`.

---

## 🧱 Adding a shared module

```bash
# Example: create a new module "new-lib"
./gradlew :new-lib:assembleDebug
```

Declare the module in `settings.gradle.kts` to include it in the project.

---

## 🧪 Tests (coming soon)

> Unit and instrumented tests will be added for each module.

---

<details>
<summary>📦 Modules & libraries used</summary>

| Module/Library       | Description                                |
|----------------------|--------------------------------------------|
| `app`                | Android app using Jetpack Compose          |
| `core_ui`            | Shared design system (theme) and MiniPlayer contracts |
| `spotifyclient`      | Spotify Remote connection & authentication |
| `spotify_fake`       | Fake ViewModel and data for the UI         |

</details>

---

## ✨ Roadmap

- 🌟 Full Spotify player integration
- 🔄 Automatic token refresh
- 🧪 Unit + e2e tests
- 🎨 Material 3 UI improvements

---

## 👨‍💻 Author

Developed by **Arnaud Vanderbecq**  
[GitHub](https://github.com/vandervdb) · [LinkedIn](https://linkedin.com/in/avanderbecq)

---

## 🪪 License

MIT
