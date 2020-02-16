package com.manickchand.androidanimelist.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manickchand.androidanimelist.models.AnimeTop
import com.manickchand.androidanimelist.models.TopResponse
import com.manickchand.androidanimelist.repository.RetrofitInit
import com.manickchand.androidanimelist.util.TAG_DEBUC
import com.manickchand.androidanimelist.util.enums.ETopType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    val animesLiveData: MutableLiveData<List<AnimeTop>> = MutableLiveData()
    val hasErrorLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun getTopAnimes(){

        RetrofitInit.service.getTop(ETopType.anime.toString()).enqueue(object:
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
}