package com.manickchand.androidanimelist.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AnimeTop  (
    var mal_id:Int?,
    var title:String?,
    var rank:Int,
    var type:String?,
    var image_url:String?,
    var episodes:Int?,
    var url:String?,
    var score:Long?,
    var start_date:String?,
    var end_date:String?,
    var members:Int?
): Parcelable