package com.manickchand.androidanimelist.ui.search

import androidx.lifecycle.MutableLiveData
import com.manickchand.androidanimelist.base.BaseViewModel
import com.manickchand.androidanimelist.models.Genre

class SearchViewModel : BaseViewModel() {

    val genreListLiveData = MutableLiveData<List<Genre>>()

    fun getGenreList(){

        genreListLiveData.value = listOf(
            Genre(1,"Action", null, null),
            Genre(2,"Adventure", null, null),
            Genre(8,"Drama", null, null),
            Genre(4,"Comedy", null, null),
            Genre(10,"Fantasy", null, null),
            Genre(24,"Sci-fi", null, null),
            Genre(19,"Music", null, null),
            Genre(22,"Romance", null, null)
        )
    }
}