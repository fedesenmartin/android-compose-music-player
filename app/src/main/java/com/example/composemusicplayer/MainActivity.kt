package com.example.composemusicplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.composemusicplayer.player.MusicPlayerController
import com.example.composemusicplayer.ui.MainScreen
import com.example.composemusicplayer.ui.theme.AppTheme
import com.example.composemusicplayer.viewmodel.PlayerViewModel

class MainActivity : ComponentActivity() {

    private lateinit var playerController: MusicPlayerController
    private val viewModel: PlayerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        playerController = MusicPlayerController(this)

        setContent {
            AppTheme {
                val uiState by viewModel.uiState.collectAsState()

                MainScreen(
                    uiState = uiState,
                    onSongClick = { index ->
                        viewModel.setCurrentIndex(index)
                        viewModel.setPlaying(true)
                        uiState.playlist.getOrNull(index)?.let { song ->
                            playerController.playSong(song) {
                                // On completion, go to next track
                                viewModel.next()
                                val nextSong = viewModel.uiState.value.playlist
                                    .getOrNull(viewModel.uiState.value.currentIndex)
                                if (nextSong != null) {
                                    playerController.playSong(nextSong)
                                }
                            }
                        }
                    },
                    onPlayPauseClick = {
                        val currentlyPlaying = playerController.isPlaying()
                        if (currentlyPlaying) {
                            playerController.pause()
                            viewModel.setPlaying(false)
                        } else {
                            val song = uiState.playlist.getOrNull(uiState.currentIndex)
                            if (song != null) {
                                if (uiState.isPlaying) {
                                    // Was playing before and only paused
                                    playerController.resume()
                                } else {
                                    playerController.playSong(song)
                                }
                                viewModel.setPlaying(true)
                            }
                        }
                    },
                    onNextClick = {
                        viewModel.next()
                        val song = viewModel.uiState.value.playlist
                            .getOrNull(viewModel.uiState.value.currentIndex)
                        if (song != null) {
                            viewModel.setPlaying(true)
                            playerController.playSong(song)
                        }
                    },
                    onPrevClick = {
                        viewModel.previous()
                        val song = viewModel.uiState.value.playlist
                            .getOrNull(viewModel.uiState.value.currentIndex)
                        if (song != null) {
                            viewModel.setPlaying(true)
                            playerController.playSong(song)
                        }
                    }
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        playerController.release()
    }
}
