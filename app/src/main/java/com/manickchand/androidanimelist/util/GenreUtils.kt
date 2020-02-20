package com.manickchand.androidanimelist.util

import com.manickchand.androidanimelist.R
import com.manickchand.androidanimelist.models.Genre

fun getGenreColor(genre: Genre): Int {
    val color = when (genre.mal_id) {
        1, 22, 9-> R.color.lightRed
        2, 37-> R.color.brown
        4, 23, 16-> R.color.teal
        8, 14-> R.color.grey
        24, 40-> R.color.lightPurple
        29, 10, 15-> R.color.yellow
        else -> R.color.blue
    }
    return color
}