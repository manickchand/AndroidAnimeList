package com.manickchand.androidanimelist.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.manickchand.androidanimelist.R
import com.manickchand.androidanimelist.models.AnimeTop
import com.manickchand.androidanimelist.util.loadImageView
import kotlinx.android.synthetic.main.item_anime.view.*

class TopAnimesAdapter(context: Context,
                       list: List<AnimeTop>,
                       val onItemClickListener:((anime:AnimeTop) -> Unit) ) : RecyclerView.Adapter<TopAnimesAdapter.MyViewHolder?>() {

    private var mContext =context
    private var mList = list
    private var mlayoutInflater: LayoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater //
    private lateinit var mView: View
    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        mView = mlayoutInflater.inflate(R.layout.item_anime,parent,false)
        return MyViewHolder(mView,onItemClickListener)
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

    inner class MyViewHolder(itemView:View,
                             private val onItemClickListener: ((anime: AnimeTop) -> Unit)) :RecyclerView.ViewHolder(itemView){

        private var ivAnime: ImageView = itemView.iv_anime

        fun bindAnime(anime: AnimeTop) {

            try {
                loadImageView(ivAnime, anime.image_url)
            }catch (e:Exception){
                e.stackTrace
            }

            itemView.setOnClickListener{
                onItemClickListener.invoke(anime)
            }
        }

    }
}