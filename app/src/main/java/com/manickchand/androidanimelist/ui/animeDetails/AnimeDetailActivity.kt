package com.manickchand.androidanimelist.ui.animeDetails

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
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
import kotlinx.android.synthetic.main.content_anime_detail.*

class AnimeDetailActivity : AppCompatActivity() {

    private lateinit var animeDetailViewModel: AnimeDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anime_detail)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }

        animeDetailViewModel = ViewModelProviders.of(this).get(AnimeDetailViewModel::class.java)

        val animeId = intent.getIntExtra(EXTRA_ANIME_ID,0)

        animeDetailViewModel.animesDetailLiveData.observe(this, Observer {
            setData(it)
        })

        iv_back.setOnClickListener{
            finish()
        }

        animeDetailViewModel.getAnime(animeId)

    }

    private fun setData(anime: AnimeDetail?) {
        val url = videoUrlToThumbUrl(anime!!.trailer_url ?: "")
        loadImageView(iv_anime_video_thumb, url)

        tv_anime_detail_score.text = anime.score.toString()
        tv_anime_title_detail.text = anime.title
        tv_anime_detail_description.text = anime.synopsis
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
