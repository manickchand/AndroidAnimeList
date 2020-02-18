package com.manickchand.androidanimelist.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AnimeDetail  (
    var mal_id:Int?,
    var description:String,
    var modified:String?,
    var image_url:String?,
    var trailer_url:String?,
    var url:String?,
    var title:String?,
    var score:Double?,
    var synopsis:String?,
    var genres:List<Genre>?
    ): Parcelable