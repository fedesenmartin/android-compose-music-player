package com.example.composemusicplayer.player

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import com.example.composemusicplayer.data.Song

class MusicPlayerController(private val context: Context) {

    private var mediaPlayer: MediaPlayer? = null
    private var currentUrl: String? = null

    fun playSong(song: Song, onCompletion: (() -> Unit)? = null) {
        if (currentUrl == song.url && mediaPlayer != null) {
            if (mediaPlayer?.isPlaying == false) {
                mediaPlayer?.start()
            }
            return
        }

        release()

        currentUrl = song.url
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
            setDataSource(song.url)
            setOnPreparedListener { start() }
            setOnCompletionListener {
                onCompletion?.invoke()
            }
            prepareAsync()
        }
    }

    fun pause() {
        mediaPlayer?.takeIf { it.isPlaying }?.pause()
    }

    fun resume() {
        mediaPlayer?.takeIf { !it.isPlaying }?.start()
    }

    fun isPlaying(): Boolean = mediaPlayer?.isPlaying == true

    fun release() {
        mediaPlayer?.release()
        mediaPlayer = null
        currentUrl = null
    }
}
