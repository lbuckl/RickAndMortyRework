package com.molchanov.domain.characters

import android.os.Parcelable
import com.molchanov.domain.IDomainData
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterPage(
    override val pageNum: Int,
    override val pageActual: Int,
    val characterList: List<Character>
) : Parcelable, IDomainData