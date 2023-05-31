package com.molchanov.feature_characters.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Класс хранящий данные о персонажах
 */
@Parcelize
data class Character(
    val id: Int,
    val name: String,
    val spec: String, //Вид
    val status: String,
    val gender: String,
    val imgUrl: String,
    val episodes: List<String>,
    val url: String
) : Parcelable