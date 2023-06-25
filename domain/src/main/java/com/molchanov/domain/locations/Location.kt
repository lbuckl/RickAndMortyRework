package com.molchanov.domain.locations

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Класс хранящий данные о локациях
 */
@Parcelize
data class Location(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String
) : Parcelable