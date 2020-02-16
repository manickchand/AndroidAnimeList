package com.manickchand.androidanimelist.ui.seasons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SeasonsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Seasons Fragment"
    }
    val text: LiveData<String> = _text
}