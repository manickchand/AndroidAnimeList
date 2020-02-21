package com.manickchand.androidanimelist.util

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.viewpager.widget.ViewPager
import com.manickchand.androidanimelist.R
import kotlinx.android.synthetic.main.fragment_seasons.view.*

class ViewPageMotion @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MotionLayout(context, attrs, defStyleAttr), ViewPager.OnPageChangeListener {

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        var numPages = 4
        progress = (position + positionOffset) / (numPages - 1)
    }

    override fun onPageSelected(position: Int) {

        when (position) {
                0 -> {
                    cl_top_seasons.background = resources.getDrawable(R.drawable.gradient_spring)
                    iv_season.setImageDrawable(resources.getDrawable(R.drawable.ic_spring))
                    tv_season_name.text = resources.getString(R.string.spring)
                }
                1 -> {
                    cl_top_seasons.background = resources.getDrawable(R.drawable.gradient_summer)
                    iv_season.setImageDrawable(resources.getDrawable(R.drawable.ic_summer))
                    tv_season_name.text = resources.getString(R.string.summer)
                }
                2 -> {
                    cl_top_seasons.background = resources.getDrawable(R.drawable.gradient_fall)
                    iv_season.setImageDrawable(resources.getDrawable(R.drawable.ic_fall))
                    tv_season_name.text = resources.getString(R.string.fall)
                }

                3 -> {
                    cl_top_seasons.background = resources.getDrawable(R.drawable.gradient_winter)
                    iv_season.setImageDrawable(resources.getDrawable(R.drawable.ic_winter))
                    tv_season_name.text = resources.getString(R.string.winter)
                }
                else -> {
                    cl_top_seasons.background = resources.getDrawable(R.drawable.gradient_spring)
                    iv_season.setImageDrawable(resources.getDrawable(R.drawable.ic_spring))
                    tv_season_name.text = resources.getString(R.string.spring)
                }
        }
    }
}