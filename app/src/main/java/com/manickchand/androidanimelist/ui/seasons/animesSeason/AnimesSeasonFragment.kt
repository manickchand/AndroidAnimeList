package com.manickchand.androidanimelist.ui.seasons.animesSeason

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.manickchand.androidanimelist.R
import com.manickchand.androidanimelist.base.BaseFragment
import com.manickchand.androidanimelist.models.Anime
import com.manickchand.androidanimelist.ui.animeDetails.AnimeDetailActivity
import com.manickchand.androidanimelist.ui.home.TopAnimesAdapter
import com.manickchand.androidanimelist.util.ARG_SEASON
import com.manickchand.androidanimelist.util.enums.ESeasons
import com.manickchand.androidanimelist.util.hasInternet
import kotlinx.android.synthetic.main.fragment_animes_season.*

class AnimesSeasonFragment : BaseFragment() {

    private var season: String? = null
    private var mList:MutableList<Anime> = ArrayList()
    private lateinit var animeSeasonViewModel: AnimeSeasonViewModel

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
        animeSeasonViewModel =
            ViewModelProviders.of(this).get(AnimeSeasonViewModel::class.java)
        return inflater.inflate(R.layout.fragment_animes_season, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setupRecyclerView()

        animeSeasonViewModel.animesLiveData.observe(this, Observer {
            it?.let { animes ->
                mList.addAll(animes)
                rv_animes_seasion.adapter!!.notifyDataSetChanged()
            }
        })

        animeSeasonViewModel.loading.observe(this, Observer { load ->
            swiperefresh.isRefreshing = load
        })

        swiperefresh.setColorSchemeResources(R.color.blue, R.color.red, R.color.yellow, R.color.brown)
        swiperefresh.setOnRefreshListener{
            this.checkConnection()
        }
    }

    fun setupRecyclerView(){

        this.checkConnection()

        with(rv_animes_seasion){

            layoutManager = GridLayoutManager(activity, 3, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
            adapter = TopAnimesAdapter(context, mList){ anime ->
                val intent = AnimeDetailActivity.getStartIntent(activity!!, anime.mal_id!!)
                activity!!.startActivity(intent)
            }
        }
    }

    override fun checkConnection(){
        if(hasInternet(activity)){
            animeSeasonViewModel.getAnimesBySeason(this.season!!)
        }else{
            Toast.makeText(context, "Connection error !", Toast.LENGTH_SHORT).show()
        }
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
