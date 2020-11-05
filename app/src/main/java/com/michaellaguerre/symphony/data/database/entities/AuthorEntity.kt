package com.michaellaguerre.symphony.data.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "authors")
data class AuthorEntity(
    @PrimaryKey var id: Int,
    var name: String,
    var userName: String,
    var email: String,
    var avatarUrl: String,
    @Embedded var address: AddressEntity
)