package com.mosangeet.ui.maintab.library.viewholder

import com.mosangeet.helper.songpreview.PreviewSong

interface BindTheme {
    fun bindTheme()
}

interface BindPreviewState {
    fun bindPreviewState(previewSong: PreviewSong?)
}

interface BindPlayingState {
    fun bindPlayingState()
}