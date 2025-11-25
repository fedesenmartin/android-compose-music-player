package com.example.composemusicplayer.viewmodel

import androidx.lifecycle.ViewModel
import com.example.composemusicplayer.data.FakeSongRepository
import com.example.composemusicplayer.data.Song
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class PlayerUiState(
    val playlist: List<Song> = emptyList(),
    val currentIndex: Int = 0,
    val isPlaying: Boolean = false
)

class PlayerViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PlayerUiState())
    val uiState: StateFlow<PlayerUiState> = _uiState

    init {
        val songs = FakeSongRepository.getSongs()
        _uiState.value = _uiState.value.copy(playlist = songs, currentIndex = 0)
    }

    fun setCurrentIndex(index: Int) {
        if (index in _uiState.value.playlist.indices) {
            _uiState.value = _uiState.value.copy(currentIndex = index)
        }
    }

    fun setPlaying(isPlaying: Boolean) {
        _uiState.value = _uiState.value.copy(isPlaying = isPlaying)
    }

    fun next() {
        val current = _uiState.value
        if (current.playlist.isEmpty()) return
        val nextIndex = (current.currentIndex + 1) % current.playlist.size
        _uiState.value = current.copy(currentIndex = nextIndex, isPlaying = true)
    }

    fun previous() {
        val current = _uiState.value
        if (current.playlist.isEmpty()) return
        val prevIndex =
            if (current.currentIndex - 1 < 0) current.playlist.lastIndex else current.currentIndex - 1
        _uiState.value = current.copy(currentIndex = prevIndex, isPlaying = true)
    }
}
