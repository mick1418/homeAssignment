package com.michaellaguerre.symphony.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Author(
    val id: Int,
    val name: String,
    val userName: String,
    val email: String,
    val avatarUrl: String,
    val address: Address
) : Parcelable