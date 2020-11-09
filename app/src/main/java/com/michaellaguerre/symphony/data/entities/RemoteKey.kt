package com.michaellaguerre.symphony.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKey (
    @PrimaryKey var label: String,
    var nextKey: Int = -1
)