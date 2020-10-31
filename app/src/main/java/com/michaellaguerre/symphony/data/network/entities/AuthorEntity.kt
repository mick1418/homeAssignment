package com.michaellaguerre.symphony.data.network.entities

data class AuthorEntity(
    val id: Int,
    val name: String,
    val userName: String,
    val email: String,
    val avatarUrl: String,
    val address: AddressEntity
)