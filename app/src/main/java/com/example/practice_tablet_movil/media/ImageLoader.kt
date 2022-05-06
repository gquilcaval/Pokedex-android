package com.example.practice_tablet_movil.media

import android.widget.ImageView

interface ImageLoader {
    fun load(url: String, imageView: ImageView)
}