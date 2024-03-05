package com.andika.yomutoki

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Seiyuu(
    val link: String,
    val name: String,
    val picture: String
): Parcelable