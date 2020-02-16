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

class SeasonsFragment : Fragment() {

    private lateinit var seasonsViewModel: SeasonsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        seasonsViewModel =
            ViewModelProviders.of(this).get(SeasonsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_seasons, container, false)
        val textView: TextView = root.findViewById(R.id.text_seasons)
        seasonsViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}