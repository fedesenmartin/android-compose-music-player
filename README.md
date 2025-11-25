# Compose Music Player

Simple Android music player built with Kotlin and Jetpack Compose.

- Plays music from **remote URLs** (no local files required).
- Basic controls: **play/pause, next, previous**.
- Minimal architecture with ViewModel + Compose.

## How to run

1. Clone the repo:

   ```bash
   git clone https://github.com/fedesenmartin/android-compose-music-player.git
   ```

2. Open the project in **Android Studio (Giraffe or newer)**.

3. Let Gradle sync finish.

4. Run the app on an emulator or physical device with **internet access**.

The playlist uses sample MP3 URLs from SoundHelix.

## Tech stack

- Kotlin
- Jetpack Compose + Material3
- MediaPlayer (URL-based streaming)
- MVVM-ish structure (ViewModel + simple repository)
