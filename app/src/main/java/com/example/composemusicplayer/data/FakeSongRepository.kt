package com.example.composemusicplayer.data

object FakeSongRepository {
    fun getSongs(): List<Song> = listOf(
        Song(
            id = 1,
            title = "Sample Song 1",
            artist = "Demo Artist",
            url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
        ),
        Song(
            id = 2,
            title = "Sample Song 2",
            artist = "Demo Artist",
            url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3"
        ),
        Song(
            id = 3,
            title = "Sample Song 3",
            artist = "Demo Artist",
            url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-3.mp3"
        )
    )
}
