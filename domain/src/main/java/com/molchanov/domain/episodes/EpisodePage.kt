package com.molchanov.domain.episodes

import android.os.Parcelable
import com.molchanov.domain.IDomainData
import kotlinx.parcelize.Parcelize

@Parcelize
data class EpisodePage(
    override val pageNum: Int,
    override val pageActual: Int,
    val episodeList: List<Episode>
) : Parcelable, IDomainData