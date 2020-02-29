package com.manickchand.androidanimelist.util

import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.manickchand.androidanimelist.R
import com.manickchand.androidanimelist.models.Genre

class GenreUtils{

    companion object {

        @JvmStatic
        fun getGenreColor(genre: Genre): Int {
            return when (genre.mal_id) {
                1, 9 -> R.color.red
                22 -> R.color.lightRed
                2, 37 -> R.color.brown
                4, 23, 16 -> R.color.teal
                8, 14 -> R.color.grey
                24, 40 -> R.color.purple
                29, 10, 15 -> R.color.yellow
                else -> R.color.blue
            }
        }

        @JvmStatic
        fun getGenreImage(genre: Genre): Int {
            return when (genre.mal_id) {
                1 -> R.drawable.ic_acao
                2 -> R.drawable.ic_aventura
                8 -> R.drawable.ic_drama
                4 -> R.drawable.ic_comedia
                10 -> R.drawable.ic_fantasia
                24 -> R.drawable.ic_scifi
                19 -> R.drawable.ic_musica
                22 -> R.drawable.ic_romance
                else -> R.drawable.ic_acao
            }
        }

        @JvmStatic
        @BindingAdapter("bind:cardBackgroundColor")
        fun setBackgroundCardColor( card: CardView,  color: Int ) {
            card.setBackgroundResource(color)
        }
    }

}