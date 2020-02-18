package com.manickchand.androidanimelist.ui.animeDetails

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.manickchand.androidanimelist.R
import com.manickchand.androidanimelist.models.AnimeDetail
import com.manickchand.androidanimelist.util.TAG_DEBUC
import com.manickchand.androidanimelist.util.loadImageView
import com.manickchand.androidanimelist.util.videoUrlToThumbUrl
import kotlinx.android.synthetic.main.activity_anime_detail.*

class AnimeDetailActivity : AppCompatActivity() {

    private lateinit var animeDetailViewModel: AnimeDetailViewModel
    private lateinit var anime:AnimeDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anime_detail)
       // setSupportActionBar(toolbar)

        animeDetailViewModel = ViewModelProviders.of(this).get(AnimeDetailViewModel::class.java)

        val animeId = intent.getIntExtra(EXTRA_ANIME_ID,0) ?: return

        animeDetailViewModel.animesDetailLiveData.observe(this, Observer {
            anime = it

            val url = videoUrlToThumbUrl(anime.trailer_url!!)
            loadImageView(iv_anime_video_thumb, url)

            //Log.i(TAG_DEBUC, ""+it.title)
        })

        animeDetailViewModel.getAnime(animeId)

    }

    companion object {
        private const val EXTRA_ANIME_ID = "EXTRA_ANIME_ID"

        fun getStartIntent(context: Context, animeId:Int): Intent {
            return Intent(context, AnimeDetailActivity::class.java).apply {
                putExtra(EXTRA_ANIME_ID, animeId)
            }
        }
    }
}
