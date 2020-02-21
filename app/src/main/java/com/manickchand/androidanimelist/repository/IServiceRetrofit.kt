package com.manickchand.androidanimelist.repository

import com.manickchand.androidanimelist.models.AnimeDetail
import com.manickchand.androidanimelist.models.SeasonResponse
import com.manickchand.androidanimelist.models.TopResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IServiceRetrofit {

    @GET("top/{type}/{page}")
    fun getTop(@Path("type") type:String, @Path("page") page:Int): Call<TopResponse>

    @GET("anime/{anime_id}")
    fun getAnimeById(@Path("anime_id") anime_id:Int): Call<AnimeDetail>

    @GET("season/{year}/{season}")
    fun getAnimesBySeason(@Path("year") year:Int, @Path("season") season:String): Call<SeasonResponse>

}