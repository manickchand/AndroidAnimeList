package com.manickchand.androidanimelist.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.manickchand.androidanimelist.databinding.ItemAnimeBinding
import com.manickchand.androidanimelist.models.Anime

class TopAnimesAdapter(context: Context,
                       list: List<Anime>,
                       val onItemClickListener:((anime:Anime) -> Unit) ) : RecyclerView.Adapter<TopAnimesAdapter.MyViewHolder?>() {

    private var mContext =context
    private var mList = list
    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val binding = ItemAnimeBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding,onItemClickListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindAnime(mList[position])
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

    inner class MyViewHolder(private val binding: ItemAnimeBinding,
                             private val onItemClickListener: ((anime: Anime) -> Unit)) :RecyclerView.ViewHolder(binding.root){

        fun bindAnime(anime: Anime) {

            binding.anime = anime
            binding.root.setOnClickListener{
                onItemClickListener.invoke(anime)
            }
        }
    }
}