package com.michaellaguerre.symphony.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comment(
    val id: Int,
    val date: String,
    val body: String,
    val userName: String,
    val email: String,
    val avatarUrl: String,
    val postId: Int
) : Parcelable