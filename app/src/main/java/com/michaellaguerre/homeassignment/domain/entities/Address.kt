package com.michaellaguerre.homeassignment.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address(
    val latitude: Double,
    val longitude: Double
) : Parcelable