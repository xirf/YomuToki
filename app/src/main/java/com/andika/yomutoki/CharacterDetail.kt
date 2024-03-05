package com.andika.yomutoki

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView


class CharacterDetail: AppCompatActivity() {

    companion object{
        const val EXTRA_CHARA = "extra_chara"
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.item_row_character)

//        val rvCharacter: RecyclerView = findViewById(R.id.anime_character_list)


    }
}