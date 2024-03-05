package com.andika.yomutoki

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val link: String,
    val name: String,
    val picture: String,
    val role: String,
    val seiyuu: Seiyuu
) : Parcelable