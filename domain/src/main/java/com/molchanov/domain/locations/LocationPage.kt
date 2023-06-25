package com.molchanov.domain.locations

import android.os.Parcelable
import com.molchanov.domain.IDomainData
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationPage(
    override val pageNum: Int,
    override val pageActual: Int,
    val locationList: List<Location>
) : Parcelable, IDomainData