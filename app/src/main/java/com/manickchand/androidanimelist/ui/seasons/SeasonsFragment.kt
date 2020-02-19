package com.manickchand.androidanimelist.ui.seasons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.manickchand.androidanimelist.R
import kotlinx.android.synthetic.main.fragment_seasons.*

class SeasonsFragment : Fragment() {

    private lateinit var seasonsViewModel: SeasonsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        seasonsViewModel =
            ViewModelProviders.of(this).get(SeasonsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_seasons, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        pager_seasons.adapter = ViewPagerAdapter(requireFragmentManager())
        tabs_seasons.setupWithViewPager(pager_seasons)
    }
}