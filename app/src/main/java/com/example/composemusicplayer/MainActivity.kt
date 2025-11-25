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
                        val song = uiState.playlist[index]
                        playerController.playSong(song)
                    },
                    onPlayPauseClick = {
                        if (!playerController.isPlaying()) {
                            // If not playing, ensure current song is loaded
                            val song = uiState.playlist.getOrNull(uiState.currentIndex)
                            if (song != null) {
                                playerController.playSong(song)
                            }
                        } else {
                            playerController.togglePlayPause()
                        }
                        viewModel.setPlaying(playerController.isPlaying())
                    },
                    onNextClick = {
                        viewModel.next()
                        val song = viewModel.uiState.value.playlist[viewModel.uiState.value.currentIndex]
                        playerController.playSong(song)
                    },
                    onPrevClick = {
                        viewModel.previous()
                        val song = viewModel.uiState.value.playlist[viewModel.uiState.value.currentIndex]
                        playerController.playSong(song)
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
