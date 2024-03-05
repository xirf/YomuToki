package com.andika.yomutoki

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListCharacterAdapter(private val listCharacter: ArrayList<Character>) :
    RecyclerView.Adapter<ListCharacterAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgCharacter: ImageView = itemView.findViewById(R.id.img_chara_photo)
        val tvCharaName: TextView = itemView.findViewById(R.id.chara_name)
        val tvCharaSeiyuu: TextView = itemView.findViewById(R.id.chara_seiyuu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_character, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listCharacter.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (link, name, picture, role, seiyuu) = listCharacter[position]
        val (_link, seiyuName, _picture) = seiyuu

        Glide.with(holder.itemView.context).load(picture).into(holder.imgCharacter)
        holder.tvCharaName.text = name
        holder.tvCharaSeiyuu.text = seiyuName
    }
}