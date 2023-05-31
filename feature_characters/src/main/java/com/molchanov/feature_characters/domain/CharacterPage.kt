package com.molchanov.feature_characters.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterPage(
    val pageNum: Int,
    val pageActual: Int,
    val characterList: List<Character>
) : Parcelable