package com.manickchand.androidanimelist.util

import com.manickchand.androidanimelist.R
import com.manickchand.androidanimelist.models.Genre

fun getGenreColor(genre: Genre): Int {
    val color = when (genre.mal_id) {
        1-> R.color.lightRed
        2-> R.color.brown
        4-> R.color.teal
        8-> R.color.grey
        24-> R.color.lightPurple
        29-> R.color.yellow
        else -> R.color.blue
    }
    return color
}