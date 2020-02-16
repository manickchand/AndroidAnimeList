package com.manickchand.androidanimelist.util

import android.widget.ImageView
import com.manickchand.androidanimelist.R
import com.squareup.picasso.Picasso

fun loadImageView(image: ImageView, imageUrl: String?) {
    Picasso.get().load(imageUrl)
        .placeholder(R.drawable.animes)
        .error(R.drawable.animes)
        .into(image)
}
