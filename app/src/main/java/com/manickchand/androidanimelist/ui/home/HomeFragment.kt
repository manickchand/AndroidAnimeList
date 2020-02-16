package com.manickchand.androidanimelist.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.manickchand.androidanimelist.R
import com.manickchand.androidanimelist.models.AnimeTop
import com.manickchand.androidanimelist.util.hasInternet
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var mList:MutableList<AnimeTop> = ArrayList()
    var pastVisiblesItems = 0
    var totalItemCount:Int = 0
    //var offset = 0
    private var loading = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setupRecyclerView()

        homeViewModel.herosLiveData.observe(this, Observer {
            it?.let { heros ->
                mList.addAll(heros)
                rv_top_anime.adapter!!.notifyDataSetChanged()
                loading = false
            }
        })

        homeViewModel.hasErrorLiveData.observe(this, Observer {error ->
            //            swiperefresh.isRefreshing = false
//            changeLayout(error)
        })

        checkConnection()

    }


    fun setupRecyclerView(){

        with(rv_top_anime){

            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL,false)
            setHasFixedSize(true)

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(
                    recyclerView: RecyclerView, dx: Int, dy: Int
                ) {
                    if (dy > 0)
                    {
                        totalItemCount = layoutManager!!.itemCount
                        pastVisiblesItems = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                        if (!loading) {
                            if (pastVisiblesItems >= totalItemCount-1) {

                                loading = true
//                                offset+= CHARACTER_LIMIT
//                                checkConnection()
                            }
                        }
                    }
                }
            })

//            adapter = HerosAdapter(this@HerosActivity, mList){ hero ->
//                val intent = DetailsActivity.getStartIntent(this@HerosActivity, hero)
//                this@HerosActivity.startActivity(intent)
//            }
        }
    }

    fun checkConnection(){
        if(hasInternet(activity)){
            //swiperefresh.isRefreshing = true
            homeViewModel.getTopAnimes()
            //changeLayout(false)
        }else{
            //changeLayout(true)
        }
    }

}