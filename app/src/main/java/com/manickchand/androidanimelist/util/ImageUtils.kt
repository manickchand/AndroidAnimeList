package com.manickchand.androidanimelist.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.manickchand.androidanimelist.R
import com.squareup.picasso.Picasso

class ImageUtils {

    companion object{

        @JvmStatic
        @BindingAdapter("bind:picassoLoad")
        fun loadImageView2(image: ImageView, imageUrl: String?) {
            if(imageUrl.isNullOrEmpty().not()){
                Picasso.get().load(imageUrl)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(image)
            }
        }

        @JvmStatic
        fun videoUrlToThumbUrl(videoUrl: String?): String {
            if(videoUrl.isNullOrEmpty().not()){
                val videoId = videoUrl!!.substringAfterLast('/').substringBefore('?')
                return "https://img.youtube.com/vi/$videoId/0.jpg"
            }else{
                return ""
            }
        }
    }
}

