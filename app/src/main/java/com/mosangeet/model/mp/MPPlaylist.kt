package com.mosangeet.model.mp

import com.mosangeet.model.Playlist

class MPPlaylist(id: Int, name: String): Playlist(id, name) {
    val songs = mutableListOf<Int>()
}