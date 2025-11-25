package com.example.composemusicplayer.data

object FakeSongRepository {
    fun getSongs(): List<Song> = listOf(
        Song(
            id = 1,
            title = "Sample Song 1",
            artist = "Demo Artist",
            url = "https://samplelib.com/lib/preview/mp3/sample-3s.mp3"
        ),
        Song(
            id = 2,
            title = "Sample Song 2",
            artist = "Demo Artist",
            url = "https://samplelib.com/lib/preview/mp3/sample-6s.mp3"
        ),
        Song(
            id = 3,
            title = "Sample Song 3",
            artist = "Demo Artist",
            url = "https://samplelib.com/lib/preview/mp3/sample-9s.mp3"
        )
    )
}
