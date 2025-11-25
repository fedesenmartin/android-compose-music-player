package com.example.composemusicplayer.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composemusicplayer.data.Song
import com.example.composemusicplayer.viewmodel.PlayerUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    uiState: PlayerUiState,
    onSongClick: (Int) -> Unit,
    onPlayPauseClick: () -> Unit,
    onNextClick: () -> Unit,
    onPrevClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Compose Music Player") })
        },
        bottomBar = {
            PlayerControlsBar(
                uiState = uiState,
                onPlayPauseClick = onPlayPauseClick,
                onNextClick = onNextClick,
                onPrevClick = onPrevClick
            )
        }
    ) { padding ->
        SongList(
            songs = uiState.playlist,
            currentIndex = uiState.currentIndex,
            modifier = Modifier.padding(padding),
            onSongClick = onSongClick
        )
    }
}

@Composable
private fun SongList(
    songs: List<Song>,
    currentIndex: Int,
    modifier: Modifier = Modifier,
    onSongClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        itemsIndexed(songs) { index, song ->
            val isCurrent = index == currentIndex
            ListItem(
                headlineContent = { Text(song.title) },
                supportingContent = { Text(song.artist) },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSongClick(index) },
                leadingContent = {
                    if (isCurrent) {
                        Text("▶")
                    }
                }
            )
            Divider()
        }
    }
}

@Composable
private fun PlayerControlsBar(
    uiState: PlayerUiState,
    onPlayPauseClick: () -> Unit,
    onNextClick: () -> Unit,
    onPrevClick: () -> Unit
) {
    Surface(tonalElevation = 3.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                val song = uiState.playlist.getOrNull(uiState.currentIndex)
                Text(text = song?.title ?: "No song")
                Text(text = song?.artist ?: "", style = MaterialTheme.typography.bodySmall)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconButton(onClick = onPrevClick) {
                    Text("⏮")
                }
                IconButton(onClick = onPlayPauseClick) {
                    Text(if (uiState.isPlaying) "⏸" else "▶")
                }
                IconButton(onClick = onNextClick) {
                    Text("⏭")
                }
            }
        }
    }
}
