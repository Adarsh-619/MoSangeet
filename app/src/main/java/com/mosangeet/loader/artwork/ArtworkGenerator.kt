package com.mosangeet.loader.artwork

import android.graphics.Bitmap
import com.mosangeet.model.Media

interface ArtworkGenerator<T> where T : Media{
    fun get(media: T): Bitmap
}