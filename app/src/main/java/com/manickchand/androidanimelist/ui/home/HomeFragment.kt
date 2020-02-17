package com.manickchand.androidanimelist.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.manickchand.androidanimelist.R
import com.manickchand.androidanimelist.models.AnimeSlider
import com.manickchand.androidanimelist.models.AnimeTop
import com.manickchand.androidanimelist.util.TAG_DEBUC
import com.manickchand.androidanimelist.util.hasInternet
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var mList:MutableList<AnimeTop> = ArrayList()
    private var pastVisiblesItems = 0
    private var totalItemCount = 0
    private var loading = false
    private var pageLoad = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setupRecyclerView()

        homeViewModel.animesLiveData.observe(this, Observer {
            it?.let { animes ->
                mList.addAll(animes)
                rv_top_anime.adapter!!.notifyDataSetChanged()
                loading = false
            }
        })

        homeViewModel.hasErrorLiveData.observe(this, Observer {error ->
            pb_top_animes.visibility = View.GONE
            if (error) Toast.makeText(context, "Error get top animes !", Toast.LENGTH_SHORT).show()
        })

        homeViewModel.animeSliderLiveData.observe(this, Observer {
            setupViewFlipper(it)
        })

        checkConnection()

    }


    fun setupRecyclerView(){

        with(rv_top_anime){

            layoutManager = GridLayoutManager(activity, 3, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
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
                                pageLoad ++
                                checkConnection()
                            }
                        }
                    }
                }
            })


            adapter = TopAnimesAdapter(context, mList){ anime ->
                Log.i(TAG_DEBUC, "Clicou ${anime.title}")
            }

        }
    }

    fun checkConnection(){
        if(hasInternet(activity)){
            pb_top_animes.visibility = View.VISIBLE
            homeViewModel.getTopAnimes(pageLoad)
            homeViewModel.getSliderAnimes()
        }else{
            pb_top_animes.visibility = View.GONE
            Toast.makeText(context, "Connection error !", Toast.LENGTH_SHORT).show()
        }
    }

    fun setupViewFlipper(list:List<AnimeSlider>){

        var inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater //

        for (i in 0..(list.size-1)){
            val view: View = inflater.inflate(R.layout.item_flipper, viewFlipper, false)

            val img = view.findViewById(R.id.iv_top_img_flipper) as ImageView
            img.setImageDrawable(resources.getDrawable(list[i].img))

            val tvName = view.findViewById(R.id.tv_top_name_flipper) as TextView
            tvName.text = getString(list[i].name)

            viewFlipper.addView(view,i)
        }

        viewFlipper.setOnClickListener{
            viewFlipper.showNext()
        }

    }

}