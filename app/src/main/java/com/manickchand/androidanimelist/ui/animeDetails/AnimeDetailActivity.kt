package com.manickchand.androidanimelist.ui.animeDetails

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.manickchand.androidanimelist.R
import com.manickchand.androidanimelist.models.AnimeDetail
import com.manickchand.androidanimelist.util.loadImageView
import com.manickchand.androidanimelist.util.videoUrlToThumbUrl
import kotlinx.android.synthetic.main.activity_anime_detail.*


class AnimeDetailActivity : AppCompatActivity() {

    private lateinit var animeDetailViewModel: AnimeDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anime_detail)
        setSupportActionBar(toolbar)
        title = ""

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
        }

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

        animeDetailViewModel.getAnime(animeId)

    }

    private fun setData(anime: AnimeDetail?) {

        val url = videoUrlToThumbUrl(anime!!.trailer_url ?: "")
        loadImageView(iv_anime_video_thumb, url)
        loadImageView(iv_anime_img_detail, anime.image_url)

        tv_anime_detail_score.text = anime.score.toString()
        title = anime.title
        tv_anime_detail_description.text = anime.synopsis
        tv_anime_rank_detail.text = anime.rank.toString()
        tv_anime_favorite_detail.text = anime.favorites.toString()

        tv_anime_episodes_detail.text = anime.episodes.toString() +" "+ getString(R.string.episodes)
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
