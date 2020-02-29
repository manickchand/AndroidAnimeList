package com.manickchand.androidanimelist.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.manickchand.androidanimelist.R
import com.manickchand.androidanimelist.base.BaseViewModel
import com.manickchand.androidanimelist.models.Anime
import com.manickchand.androidanimelist.models.AnimeSlider
import com.manickchand.androidanimelist.models.TopResponse
import com.manickchand.androidanimelist.repository.RetrofitInit
import com.manickchand.androidanimelist.util.TAG_DEBUC
import com.manickchand.androidanimelist.util.enums.ETopType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : BaseViewModel() {

    val animesLiveData = MutableLiveData<List<Anime>>()
    val hasErrorLiveData = MutableLiveData<Boolean>()
    val animeSliderLiveData = MutableLiveData<List<AnimeSlider>>()

    fun getTopAnimes(page:Int){

        loading.value = true

        RetrofitInit.service.getTop(ETopType.anime.toString(), page).enqueue(object:
            Callback<TopResponse> {

            override fun onFailure(call: Call<TopResponse>, t: Throwable) {
                Log.e(TAG_DEBUC,"[Error getTopAnimes] "+t.message)
                hasErrorLiveData.value = true
                loading.value = false
            }
            override fun onResponse(
                call: Call<TopResponse>,
                response: Response<TopResponse>
            ) {

                loading.value = false
                if(response.isSuccessful){
                    hasErrorLiveData.value = false
                    val animesTop = response.body()?.top ?: emptyList()
                    animesLiveData.value = animesTop
                }else{
                    Log.e(TAG_DEBUC,"[Response getTopAnimes Error] code: "+response.code())
                    hasErrorLiveData.value = true
                }
            }
        })
    }

    fun getSliderAnimes(){

        animeSliderLiveData.value = listOf(
                AnimeSlider(R.drawable.naruto, R.string.naruto),
                AnimeSlider(R.drawable.berserk, R.string.berserk),
                AnimeSlider(R.drawable.bleach, R.string.bleach),
                AnimeSlider(R.drawable.dragonball, R.string.dragonball)
            )
    }
}