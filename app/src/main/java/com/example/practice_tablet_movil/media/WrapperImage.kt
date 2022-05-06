package com.example.practice_tablet_movil.media

import android.widget.ImageView

object WrapperImage: ImageLoader {
    val picasso = PicassoImage()
    override fun load(url: String, imageView: ImageView) {
        picasso.load(url, imageView)
    }
}