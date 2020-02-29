package com.manickchand.androidanimelist.ui.seasons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.manickchand.androidanimelist.R
import com.manickchand.androidanimelist.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_seasons.*

class SeasonsFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_seasons, container, false)
    }

    override fun checkConnection() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        pager_seasons.adapter = ViewPagerAdapter(requireFragmentManager())
        tabs_seasons.setupWithViewPager(pager_seasons)
        pager_seasons.addOnPageChangeListener(ml_top_seasons)
    }
}