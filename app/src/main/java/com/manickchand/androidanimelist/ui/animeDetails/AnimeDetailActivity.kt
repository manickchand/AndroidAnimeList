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
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.manickchand.androidanimelist.R
import com.manickchand.androidanimelist.databinding.ActivityAnimeDetailBinding
import com.manickchand.androidanimelist.models.Anime
import com.manickchand.androidanimelist.models.Genre
import com.manickchand.androidanimelist.util.getGenreColor
import com.manickchand.androidanimelist.util.videoUrlToThumbUrl
import kotlinx.android.synthetic.main.activity_anime_detail.*


class AnimeDetailActivity : AppCompatActivity() {

    private lateinit var animeDetailViewModel: AnimeDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anime_detail)
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        val binding = DataBindingUtil
            .setContentView<ActivityAnimeDetailBinding>( this, R.layout.activity_anime_detail )

        binding.lifecycleOwner = this

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }

        animeDetailViewModel = ViewModelProviders.of(this).get(AnimeDetailViewModel::class.java)

        binding.viewModel = animeDetailViewModel

        val animeId = intent.getIntExtra(EXTRA_ANIME_ID,0)

        animeDetailViewModel._animesDetailLiveData.observe(this, Observer {
            setGenres(it.genres ?: emptyList())
        })

        animeDetailViewModel.getAnime(animeId)

    }

    private fun setData(anime: Anime?) {

        val url = videoUrlToThumbUrl(anime!!.trailer_url ?: "")
//        loadImageView(iv_anime_video_thumb, url)
//        loadImageView(iv_anime_img_detail, anime.image_url)
//
//
//        setGenres(anime.genres ?: emptyList())

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
