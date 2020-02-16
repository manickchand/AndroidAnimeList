package com.manickchand.androidanimelist.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genre  (
    var mal_id:Int?,
    var name:String,
    var type:String,
    var url:String?
    ): Parcelable