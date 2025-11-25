package com.example.composemusicplayer.player

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import com.example.composemusicplayer.data.Song

class MusicPlayerController(private val context: Context) {

    private var mediaPlayer: MediaPlayer? = null

    private fun createMediaPlayer(url: String): MediaPlayer {
        return MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(url)
            setOnPreparedListener { start() }
            setOnCompletionListener {
                // Do nothing here; ViewModel will manage next/prev.
            }
            prepareAsync()
        }
    }

    fun playSong(song: Song) {
        stop()
        mediaPlayer = createMediaPlayer(song.url)
    }

    fun togglePlayPause() {
        val player = mediaPlayer ?: return
        if (player.isPlaying) player.pause() else player.start()
    }

    fun isPlaying(): Boolean = mediaPlayer?.isPlaying ?: false

    fun stop() {
        mediaPlayer?.apply {
            stop()
            reset()
            release()
        }
        mediaPlayer = null
    }

    fun release() {
        stop()
    }
}
