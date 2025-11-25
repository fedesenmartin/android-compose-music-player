# Android Compose Music Player

Simple music player built with **Kotlin + Jetpack Compose** that plays songs from **remote URLs** using `MediaPlayer`.

## Features

- Playlist with hardcoded demo songs (MP3 URLs).
- Play / Pause.
- Next / Previous track.
- Minimal dark Material3 UI with Jetpack Compose.

## Requirements

- Android Studio Hedgehog or newer.
- Android SDK 24+.

## How to run

1. Clone the repo:

   ```bash
   git clone https://github.com/fedesenmartin/android-compose-music-player.git
   cd android-compose-music-player
   ```

2. Open the project in **Android Studio**.

3. Let Gradle sync.

4. Run the `app` configuration on a device or emulator with internet access.

## Notes

- Demo songs are short MP3 clips hosted on `samplelib.com`. You can change URLs in:

  ```kotlin
  app/src/main/java/com/example/composemusicplayer/data/FakeSongRepository.kt
  ```

- The app requests the `INTERNET` permission in the manifest to stream the audio.
