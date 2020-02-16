package com.manickchand.androidanimelist.models

data class TopResponse  (
    var request_hash:String?,
    var request_cached:Boolean,
    var request_cache_expiry:Int,
    var top:List<AnimeTop>?
)