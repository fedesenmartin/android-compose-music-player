package com.example.composemusicplayer

import com.example.composemusicplayer.viewmodel.PlayerViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

class PlayerViewModelTest {

    @Test
    fun `next moves to next song`() {
        val vm = PlayerViewModel()
        val firstIndex = vm.uiState.value.currentIndex
        vm.next()
        val secondIndex = vm.uiState.value.currentIndex
        assertEquals((firstIndex + 1) % vm.uiState.value.playlist.size, secondIndex)
    }
}
