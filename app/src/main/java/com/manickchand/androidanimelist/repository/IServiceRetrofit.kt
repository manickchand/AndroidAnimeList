package com.manickchand.androidanimelist.repository

import com.manickchand.androidanimelist.models.TopResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IServiceRetrofit {

    @GET("top/{type}/{page}")
    fun getTop(@Path("type") type:String, @Path("page") page:Int): Call<TopResponse>

}