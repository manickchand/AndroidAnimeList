package com.manickchand.androidanimelist.ui.animeDetails

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manickchand.androidanimelist.models.AnimeDetail
import com.manickchand.androidanimelist.repository.RetrofitInit
import com.manickchand.androidanimelist.util.TAG_DEBUC
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnimeDetailViewModel : ViewModel(){

    val animesDetailLiveData = MutableLiveData<AnimeDetail>()
    val hasErrorLiveData = MutableLiveData<Boolean>()

    fun getAnime(anime_id:Int){

        RetrofitInit.service.getAnimeById(anime_id).enqueue(object:
            Callback<AnimeDetail> {

            override fun onFailure(call: Call<AnimeDetail>, t: Throwable) {
                Log.e(TAG_DEBUC,"[Error getAnime] "+t.message)
                hasErrorLiveData.value = true
            }
            override fun onResponse(
                call: Call<AnimeDetail>,
                response: Response<AnimeDetail>
            ) {

                if(response.isSuccessful){
                    hasErrorLiveData.value = false
                    val animesTop = response.body() ?: null
                    animesDetailLiveData.value = animesTop
                }else{
                    Log.e(TAG_DEBUC,"[Response getAnime Error] code: "+response.code())
                    hasErrorLiveData.value = true
                }
            }
        })
    }

}