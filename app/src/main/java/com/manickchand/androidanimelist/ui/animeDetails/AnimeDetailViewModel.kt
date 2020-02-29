package com.manickchand.androidanimelist.ui.animeDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.manickchand.androidanimelist.base.BaseViewModel
import com.manickchand.androidanimelist.models.Anime
import com.manickchand.androidanimelist.repository.RetrofitInit
import com.manickchand.androidanimelist.util.TAG_DEBUC
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnimeDetailViewModel : BaseViewModel(){

    val _animesDetailLiveData = MutableLiveData<Anime>()
    val anime: LiveData<Anime> = Transformations.map(_animesDetailLiveData) { it }
    val load: LiveData<Boolean> = Transformations.map(loading) { it }

    fun getAnime(anime_id:Int){

        loading.value = true

        RetrofitInit.service.getAnimeById(anime_id).enqueue(object:
            Callback<Anime> {

            override fun onFailure(call: Call<Anime>, t: Throwable) {
                Log.e(TAG_DEBUC,"[Error getAnime] "+t.message)
                loading.value = false
            }
            override fun onResponse(
                call: Call<Anime>,
                response: Response<Anime>
            ) {
                loading.value = false
                if(response.isSuccessful){
                    val animesTop = response.body() ?: null
                    _animesDetailLiveData.value = animesTop
                }else{
                    Log.e(TAG_DEBUC,"[Response getAnime Error] code: "+response.code())
                }
            }
        })
    }
}