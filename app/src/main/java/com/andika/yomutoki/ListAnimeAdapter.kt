package com.andika.yomutoki

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListAnimeAdapter(private val listAnime: ArrayList<Anime>) :
    RecyclerView.Adapter<ListAnimeAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgCover: ImageView = itemView.findViewById(R.id.img_item_cover)
        val tvRating: TextView = itemView.findViewById(R.id.tv_item_rating)
        val tvSynopsis: TextView = itemView.findViewById(R.id.tv_item_synopsis)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_item_title)
        val tvType: TextView = itemView.findViewById(R.id.tv_item_type)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_anime, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listAnime.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (title, englishTitle, japaneseTitle, synopsis, episodes, type, rated, genres, studios, status, picture, animeCover, characters) = listAnime[position]
        Glide.with(holder.itemView.context).load(animeCover).into(holder.imgCover)
        holder.tvTitle.text = title
        holder.tvSynopsis.text = synopsis
        holder.tvRating.text = "${String.format("%.1f", rated)}/10.0"
        holder.tvType.text = type

        holder.itemView.setOnClickListener{
            val intentDetail = Intent(holder.itemView.context, AnimeDetail::class.java)
            intentDetail.putExtra("extra_anime", listAnime[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }
}