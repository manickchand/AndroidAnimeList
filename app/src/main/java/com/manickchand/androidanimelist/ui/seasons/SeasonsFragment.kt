package com.manickchand.androidanimelist.ui.seasons

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.manickchand.androidanimelist.R
import com.manickchand.androidanimelist.util.TAG_DEBUC
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

        pager_seasons.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
                Log.i(TAG_DEBUC, "onPageScrollStateChanged ${state}")
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                Log.i(TAG_DEBUC, "onPageScrolled pos: ${position}")
            }
            override fun onPageSelected(position: Int) {
                settupSeasonTop(position)
            }

        })
    }

    fun settupSeasonTop(pos:Int){

        when (pos) {
            0 -> {
                cl_top_seasons.background = resources.getDrawable(R.drawable.gradient_spring)
                iv_season.setImageDrawable(resources.getDrawable(R.drawable.ic_spring))
                tv_season_name.text = getString(R.string.spring)
            }
            1 -> {
                cl_top_seasons.background = resources.getDrawable(R.drawable.gradient_summer)
                iv_season.setImageDrawable(resources.getDrawable(R.drawable.ic_summer))
                tv_season_name.text = getString(R.string.summer)
            }
            2 -> {
                cl_top_seasons.background = resources.getDrawable(R.drawable.gradient_fall)
                iv_season.setImageDrawable(resources.getDrawable(R.drawable.ic_fall))
                tv_season_name.text = getString(R.string.fall)
            }

            3 -> {
                cl_top_seasons.background = resources.getDrawable(R.drawable.gradient_winter)
                iv_season.setImageDrawable(resources.getDrawable(R.drawable.ic_winter))
                tv_season_name.text = getString(R.string.winter)
            }
            else -> {
                cl_top_seasons.background = resources.getDrawable(R.drawable.gradient_spring)
                iv_season.setImageDrawable(resources.getDrawable(R.drawable.ic_spring))
                tv_season_name.text = getString(R.string.spring)
            }
        }

    }
}