package com.manickchand.androidanimelist.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AnimeDetail  (
    var mal_id:Int?,
    var name:String,
    var description:String,
    var modified:String?,
    var image_url:String?,
    var trailer_url:String?,
    var url:String?,
    var title:String?,
    var score:Long?,
    var synopsis:String?,
    var genres:List<Genre>?
    ): Parcelable


//{
//    "request_hash": "request:anime:51ea8b7946adfc41c0c497aec9ab256ac8c696ca",
//    "request_cached": true,
//    "request_cache_expiry": 70069,
//    "mal_id": 39792,
//    "url": "https://myanimelist.net/anime/39792/Eizouken_ni_wa_Te_wo_Dasu_na",
//
//
//     "title_english": "Keep Your Hands Off Eizouken!",
//    "title_japanese": "映像研には手を出すな!",
//    "title_synonyms": [
//    "Hands off the Motion Pictures Club!"
//    ],
//    "type": "TV",
//    "source": "Manga",
//    "episodes": 12,
//    "status": "Currently Airing",
//    "airing": true,
//    "aired": {
//    "from": "2020-01-06T00:00:00+00:00",
//    "to": null,
//    "prop": {
//        "from": {
//        "day": 6,
//        "month": 1,
//        "year": 2020
//    },
//        "to": {
//        "day": null,
//        "month": null,
//        "year": null
//    }
//    },
//    "string": "Jan 6, 2020 to ?"
//},
//    "duration": "25 min per ep",
//    "rating": "PG-13 - Teens 13 or older",
//
//    "scored_by": 15759,
//    "rank": 311,
//    "popularity": 1224,
//    "members": 90280,
//    "favorites": 439,
//    "background": null,
//    "premiered": "Winter 2020",
//    "broadcast": "Mondays at 00:10 (JST)",
//    "related": {
//    "Adaptation": [
//    {
//        "mal_id": 112087,
//        "type": "manga",
//        "name": "Eizouken ni wa Te wo Dasuna!",
//        "url": "https://myanimelist.net/manga/112087/Eizouken_ni_wa_Te_wo_Dasuna"
//    }
//    ]
//},
//    "producers": [],
//    "licensors": [],
//    "studios": [
//    {
//        "mal_id": 1591,
//        "type": "anime",
//        "name": "Science SARU",
//        "url": "https://myanimelist.net/anime/producer/1591/Science_SARU"
//    }
//    ],
//    ,
//    "opening_themes": [
//    "\"Easy Breezy\" by chelmico"
//    ],
//    "ending_themes": [
//    "\"Namae no Nai Ao (名前のない青)\" by Kamisama, Boku wa Kizuite shimatta (神様、僕は気づいてしまった)"
//    ]
//}