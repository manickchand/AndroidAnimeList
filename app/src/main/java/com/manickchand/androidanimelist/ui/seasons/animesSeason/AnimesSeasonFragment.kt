package com.manickchand.androidanimelist.ui.seasons.animesSeason

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.manickchand.androidanimelist.R
import com.manickchand.androidanimelist.util.ARG_SEASON
import com.manickchand.androidanimelist.util.enums.ESeasons
import kotlinx.android.synthetic.main.fragment_animes_season.*

class AnimesSeasonFragment : Fragment() {

    private var season: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            season = it.getString(ARG_SEASON)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_animes_season, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        tv_an_season.text = season

    }


    companion object {
        @JvmStatic
        fun newInstance(season: ESeasons ) =
            AnimesSeasonFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_SEASON, season.toString())
                }
            }
    }
}
