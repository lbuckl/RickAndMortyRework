package com.molchanov.domain.episodes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Класс хранящий данные об эпизодах
 */
@Parcelize
data class Episode(
    val id: Int,
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<String>,
    val url: String
) : Parcelable