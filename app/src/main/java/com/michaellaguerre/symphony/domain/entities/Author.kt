package com.michaellaguerre.symphony.domain.entities

import com.michaellaguerre.symphony.data.network.entities.AddressEntity

data class Author(
    val id: Int,
    val name: String,
    val userName: String,
    val email: String,
    val avatarUrl: String,
    val address: AddressEntity
)