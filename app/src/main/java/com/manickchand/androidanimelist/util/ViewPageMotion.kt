package com.manickchand.androidanimelist.util

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.viewpager.widget.ViewPager

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
    }
}