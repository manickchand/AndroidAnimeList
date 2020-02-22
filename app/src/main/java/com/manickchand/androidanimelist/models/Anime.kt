package com.manickchand.androidanimelist.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Anime  (
    var mal_id:Int?,
    var image_url:String?,
    var trailer_url:String?,
    var url:String?,
    var title:String?,
    var score:Double?,
    var synopsis:String?,
    var episodes:Int?,
    var favorites:Int?,
    var rank:Int?,
    var type:String?,
    var genres:List<Genre>?
    ): Parcelable