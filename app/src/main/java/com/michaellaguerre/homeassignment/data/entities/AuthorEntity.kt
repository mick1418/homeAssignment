package com.michaellaguerre.homeassignment.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.michaellaguerre.homeassignment.domain.entities.Author

@Entity(tableName = "authors")
data class AuthorEntity(
    @PrimaryKey var id: Int,
    var name: String,
    var userName: String,
    var email: String,
    var avatarUrl: String,
    @Embedded var address: AddressEntity
) {

    /**
     * Conversion to domain entity.
     */
    fun toAuthor() = Author(id, name, userName, email, avatarUrl, address.toAddress())


    companion object {
        val empty = AuthorEntity(
            0, "", "", "",
            "", AddressEntity.empty
        )
    }
}