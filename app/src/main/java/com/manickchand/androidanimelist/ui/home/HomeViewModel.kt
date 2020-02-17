package com.manickchand.androidanimelist.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manickchand.androidanimelist.R
import com.manickchand.androidanimelist.models.AnimeSlider
import com.manickchand.androidanimelist.models.AnimeTop
import com.manickchand.androidanimelist.models.TopResponse
import com.manickchand.androidanimelist.repository.RetrofitInit
import com.manickchand.androidanimelist.util.TAG_DEBUC
import com.manickchand.androidanimelist.util.enums.ETopType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    val animesLiveData = MutableLiveData<List<AnimeTop>>()
    val hasErrorLiveData = MutableLiveData<Boolean>()
    val animeSliderLiveData = MutableLiveData<List<AnimeSlider>>()

    fun getTopAnimes(page:Int){

        RetrofitInit.service.getTop(ETopType.anime.toString(), page).enqueue(object:
            Callback<TopResponse> {

            override fun onFailure(call: Call<TopResponse>, t: Throwable) {
                Log.e(TAG_DEBUC,"[Error getTopAnimes] "+t.message)
                hasErrorLiveData.value = true
            }
            override fun onResponse(
                call: Call<TopResponse>,
                response: Response<TopResponse>
            ) {

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