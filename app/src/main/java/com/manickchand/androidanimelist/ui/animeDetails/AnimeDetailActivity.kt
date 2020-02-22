package com.manickchand.androidanimelist.ui.animeDetails

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.manickchand.androidanimelist.R
import com.manickchand.androidanimelist.models.Anime
import com.manickchand.androidanimelist.models.Genre
import com.manickchand.androidanimelist.util.getGenreColor
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

        pb_detail_anime.visibility = View.VISIBLE
        animeDetailViewModel.getAnime(animeId)

    }

    private fun setData(anime: Anime?) {

        pb_detail_anime.visibility = View.GONE

        val url = videoUrlToThumbUrl(anime!!.trailer_url ?: "")
        loadImageView(iv_anime_video_thumb, url)
        loadImageView(iv_anime_img_detail, anime.image_url)

        tv_anime_detail_score.text = anime.score.toString()
        title = anime.title
        tv_anime_detail_description.text = anime.synopsis
        tv_anime_rank_detail.text = anime.rank.toString()
        tv_anime_favorite_detail.text = anime.favorites.toString()

        tv_anime_episodes_detail.text = anime.episodes.toString() +" "+ getString(R.string.episodes)

        setGenres(anime.genres ?: emptyList())

    }

    fun setGenres(list:List<Genre>){
        var inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater //

        for (genre in list){
            val view: View = inflater.inflate(R.layout.item_genre_detail, flexboxlayout, false)

            val tvGenre = view.findViewById(R.id.tv_genre_detail) as TextView
            tvGenre.text = genre.name
            tvGenre.setTextColor(resources.getColor(getGenreColor(genre)))

            flexboxlayout.addView(view)
        }

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
