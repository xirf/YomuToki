package com.andika.yomutoki

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson

class AnimeDetail : AppCompatActivity() {
    companion object {
        const val EXTRA_ANIME = "extra_anime"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_anime_detail)

        val ivCover: ImageView = findViewById(R.id.anime_cover)
        val tvTitle: TextView = findViewById(R.id.anime_title)
        val ivHeroPicture: ImageView = findViewById(R.id.anime_hero_picture)
        val tvGenres: TextView = findViewById(R.id.anime_detail_genres)
        val tvType: TextView = findViewById(R.id.anime_detail_type)
        val tvRating: TextView = findViewById(R.id.tv_detail_rating)
        val tvSynopsis: TextView = findViewById(R.id.anime_detail_synopsis)
        val btnBack: ImageButton = findViewById(R.id.anime_detail_back)
        val btnShare: ImageButton = findViewById(R.id.btn_anime_share)
        val btnWatch: Button = findViewById(R.id.btn_anime_watch)
        val tbTitle: TextView = findViewById(R.id.table_title)
        val tbEnTitle: TextView = findViewById(R.id.table_en_title)
        val tbJpTitle: TextView = findViewById(R.id.table_jp_title)
        val tbEpisode: TextView = findViewById(R.id.table_episodes)
        val tbType: TextView = findViewById(R.id.table_type)
        val tbStatus: TextView = findViewById(R.id.table_status)
        val tbStudios: TextView = findViewById(R.id.table_studios)


        val anime = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_ANIME, Anime::class.java)
        } else {
            @Suppress("DEPRECATION") intent.getParcelableExtra(EXTRA_ANIME)
        }
        if (anime != null) {
            val (title, englishTitle, japaneseTitle, synopsis, episodes, type, rated, genres, studios, status, picture, animeCover, characters, url) = anime

            val textToShare = """See $title
English Title: $englishTitle
Japanese Title: $japaneseTitle
Genres: $genres
Synopsis: $synopsis

It has $episodes episodes and it's rated $rated.
Status: $status
Type: $type
Studios: $studios

$url""".trimIndent()


            Glide.with(this).load(animeCover).into(ivCover)
            Glide.with(this).load(picture).into(ivHeroPicture)
            tvTitle.text = title
            tvGenres.text = genres
            tvRating.text = "${String.format("%.1f", rated)}/10.0"
            tvSynopsis.text = synopsis
            tvType.text = type
            tbTitle.text = title
            tbEpisode.text = episodes.toString()
            tbEnTitle.text = englishTitle
            tbJpTitle.text = japaneseTitle
            tbType.text = type
            tbStatus.text = status
            tbStudios.text = studios

            val characterList = parseJSON(characters)
            val rvCharacter: RecyclerView = findViewById(R.id.anime_character_list)
            val characterCollection = ArrayList<Character>()

            for (i in characterList.indices) {
                val character = Character(
                    name = characterList[i].name,
                    link = characterList[i].link,
                    seiyuu = characterList[i].seiyuu,
                    picture = characterList[i].picture,
                    role = characterList[i].role
                )
                characterCollection.add(character)
            }

            rvCharacter.layoutManager =
                LinearLayoutManager(rvCharacter.context, LinearLayoutManager.HORIZONTAL, false)
            val listCharacterAdapter = ListCharacterAdapter(characterCollection)
            rvCharacter.adapter = listCharacterAdapter

            // Listener
            btnBack.setOnClickListener { finish() }
            btnShare.setOnClickListener {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, textToShare)
                startActivity(Intent.createChooser(shareIntent, "Share link via"))
            }
            btnWatch.setOnClickListener {
                val viewIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(viewIntent)
            }
        }

    }


    private fun parseJSON(jsonString: String): List<Character> {
        val gson = Gson()
        return gson.fromJson(jsonString, Array<Character>::class.java).toList()
    }

}