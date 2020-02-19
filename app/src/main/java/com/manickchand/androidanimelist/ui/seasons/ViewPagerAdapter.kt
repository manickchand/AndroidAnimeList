package com.manickchand.androidanimelist.ui.seasons

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.manickchand.androidanimelist.ui.seasons.animesSeason.AnimesSeasonFragment
import com.manickchand.androidanimelist.util.enums.ESeasons

class ViewPagerAdapter(
    supportFragmentManager: FragmentManager
) : FragmentStatePagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    data class Page(val title: String, val frag: () -> Fragment)

    @Suppress("MoveLambdaOutsideParentheses")
    private val pages = listOf(
        Page(
            ESeasons.spring.toString(),
            { AnimesSeasonFragment.newInstance(ESeasons.spring) }
        ),
        Page(
            ESeasons.summer.toString(),
            { AnimesSeasonFragment.newInstance(ESeasons.summer) }
        ),
        Page(
            ESeasons.fall.toString(),
            { AnimesSeasonFragment.newInstance(ESeasons.fall) }
        ),
        Page(
            ESeasons.winter.toString(),
            { AnimesSeasonFragment.newInstance(ESeasons.winter) }
        )
    )

    override fun getItem(position: Int): Fragment {
        return pages[position].frag()
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pages[position].title
    }
}
