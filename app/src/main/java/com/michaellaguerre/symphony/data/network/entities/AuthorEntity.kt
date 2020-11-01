package com.michaellaguerre.symphony.data.network.entities

import com.michaellaguerre.symphony.domain.entities.Author

data class AuthorEntity(
    val id: Int,
    val name: String,
    val userName: String,
    val email: String,
    val avatarUrl: String,
    val address: AddressEntity
) {

    /**
     * Conversion to domain entity.
     */
    fun toAuthor() = Author(
        id,
        name,
        userName,
        email,
        avatarUrl,
        address
    )
}