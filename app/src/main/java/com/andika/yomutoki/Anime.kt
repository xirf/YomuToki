package com.andika.yomutoki

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Anime(
    val title: String,
    val englishTitle: String,
    val japaneseTitle: String,
    val synopsis: String,
    val episodes: Int,
    val type: String,
    val rated: Float,
    val genres: String,
    val studios: String,
    val status: String,
    val picture: String,
    val animeCover: String,
    val characters: String,
    val url: String
) : Parcelable
