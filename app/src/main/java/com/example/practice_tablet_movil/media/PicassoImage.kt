package com.example.practice_tablet_movil.media

import android.widget.ImageView
import com.squareup.picasso.Picasso

class PicassoImage: ImageLoader {
    override fun load(url: String, imageView: ImageView) {
        Picasso.get().load(url).resize(350,350).centerCrop().into(imageView)
    }
}