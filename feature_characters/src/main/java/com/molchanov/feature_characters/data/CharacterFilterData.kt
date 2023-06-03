package com.molchanov.feature_characters.data

import com.molchanov.core.domain.filter.BaseFilterData
import javax.inject.Inject

data class CharacterFilterData @Inject constructor(
    val name: String?,
    val status: String?,
    val species: String?,
    val gender: String?
) : BaseFilterData()

fun CharacterFilterData.getRoomFormat(): CharacterFilterData {
    return CharacterFilterData(
        if (this.name.isNullOrBlank()) "%%" else "%${this.name}%",
        if (this.status.isNullOrBlank()) "%%" else this.status,
        if (this.species.isNullOrBlank()) "%%" else "%${this.species}%",
        if (this.gender.isNullOrBlank()) "%%" else this.gender
    )
}