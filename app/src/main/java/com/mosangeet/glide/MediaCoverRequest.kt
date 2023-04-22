package com.mosangeet.glide

import android.content.Context
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mosangeet.R
import com.bumptech.glide.RequestManager
import com.mosangeet.model.Song
import android.graphics.Bitmap
import android.os.Build
import com.mosangeet.glide.audiocover.AudioFileCover
import com.mosangeet.util.MusicUtil
import com.bumptech.glide.signature.MediaStoreSignature
import com.mosangeet.util.PreferenceUtil
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.Key
import com.mosangeet.model.Media

object MediaCoverRequest {
    val defaultDiskCacheStrategy: DiskCacheStrategy = DiskCacheStrategy.NONE
    const val defaultErrorImage = R.drawable.ic_music_style
    const val defaultAnimation = android.R.anim.fade_in
}