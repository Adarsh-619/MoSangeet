package com.mosangeet.glide;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.mosangeet.glide.artistimage.ArtistImage;
import com.mosangeet.glide.artistimage.ArtistImageLoader;
import com.mosangeet.glide.audiocover.AudioFileCover;
import com.mosangeet.glide.audiocover.AudioFileCoverLoader;
import com.mosangeet.glide.palette.BitmapPaletteTranscoder;
import com.mosangeet.glide.palette.BitmapPaletteWrapper;


import java.io.InputStream;

@GlideModule
public class MPGlideModule extends AppGlideModule {

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        super.registerComponents(context, glide, registry);
        registry.prepend(ArtistImage.class, InputStream.class, new ArtistImageLoader.Factory(context));
        registry.prepend(AudioFileCover.class, InputStream.class, new AudioFileCoverLoader.Factory());

        registry.register(Bitmap.class, BitmapPaletteWrapper.class, new BitmapPaletteTranscoder());
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
    }
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
