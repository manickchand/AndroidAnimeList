package com.manickchand.androidanimelist.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.manickchand.androidanimelist.R
import com.squareup.picasso.Picasso


fun loadImageView(image: ImageView, imageUrl: String?) {
    Picasso.get().load(imageUrl)
        .placeholder(R.drawable.placeholder)
        .error(R.drawable.placeholder)
        .into(image)
}

fun videoUrlToThumbUrl(videoUrl: String): String {
    val videoId = videoUrl.substringAfterLast('/').substringBefore('?')
    return "https://img.youtube.com/vi/$videoId/0.jpg"
}

