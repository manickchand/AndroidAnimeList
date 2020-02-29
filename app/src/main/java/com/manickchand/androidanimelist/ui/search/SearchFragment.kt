package com.manickchand.androidanimelist.ui.search

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
import com.manickchand.androidanimelist.models.Genre
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : BaseFragment() {

    private lateinit var searchViewModel:SearchViewModel
    private var mList:MutableList<Genre> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        with(rv_genres_Search){
            layoutManager = GridLayoutManager(activity, 2, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = SearchGenreAdapter(context, mList){ genre ->
                Toast.makeText(activity, "Click ${genre.name}", Toast.LENGTH_SHORT).show()
            }
        }

        searchViewModel.genreListLiveData.observe(this, Observer { list ->
            mList.addAll(list)
            rv_genres_Search.adapter!!.notifyDataSetChanged()
        })

        searchViewModel.getGenreList()
    }

    override fun checkConnection() {}

}