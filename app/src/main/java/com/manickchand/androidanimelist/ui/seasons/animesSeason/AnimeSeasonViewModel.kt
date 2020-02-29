package com.manickchand.androidanimelist.ui.seasons.animesSeason

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manickchand.androidanimelist.base.BaseViewModel
import com.manickchand.androidanimelist.models.Anime
import com.manickchand.androidanimelist.models.SeasonResponse
import com.manickchand.androidanimelist.repository.RetrofitInit
import com.manickchand.androidanimelist.util.TAG_DEBUC
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnimeSeasonViewModel : BaseViewModel() {

    val animesLiveData = MutableLiveData<List<Anime>>()

    fun getAnimesBySeason(season:String){

        loading.value = true

        RetrofitInit.service.getAnimesBySeason(2019, season).enqueue(object:
            Callback<SeasonResponse> {

            override fun onFailure(call: Call<SeasonResponse>, t: Throwable) {
                loading.value = false
                Log.e(TAG_DEBUC,"[Error getTopAnimes] "+t.message)
            }
            override fun onResponse(
                call: Call<SeasonResponse>,
                response: Response<SeasonResponse>
            ) {
                loading.value = false
                if(response.isSuccessful){
                    val animesTop = response.body()?.anime ?: emptyList()
                    animesLiveData.value = animesTop
                }else{
                    Log.e(TAG_DEBUC,"[Response getTopAnimes Error] code: "+response.code())
                }
            }
        })
    }

}