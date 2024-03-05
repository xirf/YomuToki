package com.andika.yomutoki

import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class AnimeDetail : AppCompatActivity() {
    companion object {
        const val EXTRA_ANIME = "extra_anime"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_anime_detail)

        val ivCover: ImageView = findViewById(R.id.anime_cover)
        val tvTitle: TextView = findViewById(R.id.anime_title)
        val ivHeroPicture: ImageView = findViewById(R.id.anime_hero_picture)

        val anime = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Anime>(EXTRA_ANIME, Anime::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Anime>(EXTRA_ANIME)
        }
        if (anime != null) {
            val (title, englishTitle, japaneseTitle, synopsis, episodes, type, rated, genres, studios, status, picture, animeCover, characters) = anime

            Glide.with(this).load(animeCover).into(ivCover)
            Glide.with(this).load(picture).into(ivHeroPicture)
            tvTitle.text = title
        }

    }

}