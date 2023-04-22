package com.mosangeet.provider

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.core.content.ContextCompat
import com.mosangeet.App
import com.mosangeet.R
import com.mosangeet.helper.extension.ResettableLazyManager
import com.mosangeet.helper.extension.resettableLazy
import com.mosangeet.utils.ColorUtils
import com.mosangeet.util.Tool

object ColorProvider {
    // color that will change due to palette only
    @JvmField
    val paletteRelatedLM = ResettableLazyManager()

    // color that will change due to dark/light mode only
    @JvmField
    val darkLightRelatedLM = ResettableLazyManager()

    val baseColor by resettableLazy(paletteRelatedLM) { Tool.getBaseColor() }

    val baseColorL45 by resettableLazy(paletteRelatedLM) { ColorUtils.lighterInternal(baseColor, 0.45f) }
    val baseColorL60 by resettableLazy(paletteRelatedLM) { ColorUtils.lighterInternal(baseColor, 0.6f) }
    val baseColorL25 by resettableLazy(paletteRelatedLM) { ColorUtils.lighterInternal(baseColor, 0.25f) }
    val baseColorAaa by resettableLazy(paletteRelatedLM) { Color.argb(0xAA, Color.red(baseColor), Color.green(baseColor), Color.blue(baseColor))}
    val baseColorStateList by resettableLazy(paletteRelatedLM) { ColorStateList.valueOf(baseColor)}

    val flatWhite by resettableLazy(darkLightRelatedLM) { ContextCompat.getColor(App.getInstance().applicationContext, R.color.FlatWhite) }
    val flatWhiteAaa by resettableLazy(darkLightRelatedLM) {
        val fW = flatWhite
        Color.argb(0xAA, Color.red(fW), Color.green(fW), Color.blue(fW))
    }

}