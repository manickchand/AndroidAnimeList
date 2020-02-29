package com.manickchand.androidanimelist.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.manickchand.androidanimelist.databinding.ItemGenreBinding
import com.manickchand.androidanimelist.models.Genre

class SearchGenreAdapter(context: Context,
                         list: List<Genre>,
                         val onItemClickListener:((genre:Genre) -> Unit) ) : RecyclerView.Adapter<SearchGenreAdapter.MyViewHolder?>() {

    private var mContext =context
    private var mList = list
    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val binding = ItemGenreBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding,onItemClickListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindGenre(mList[position])
        setAnimation(holder.itemView, position)
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > lastPosition) {
            val animation: Animation =
                AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    override fun getItemCount() = mList.count()

    inner class MyViewHolder(private val binding: ItemGenreBinding,
                             private val onItemClickListener: ((genre: Genre) -> Unit)) :RecyclerView.ViewHolder(binding.root){

        fun bindGenre(genre: Genre) {
            binding.genre = genre
            binding.root.setOnClickListener{
                onItemClickListener.invoke(genre)
            }
        }
    }
}