package com.michaellaguerre.symphony.data.entities

import com.michaellaguerre.symphony.domain.entities.Address

data class AddressEntity(
    var latitude: String,
    var longitude: String
) {

    /**
     * Conversion to domain entity.
     */
    fun toAddress() = Address(latitude.toDouble(), longitude.toDouble())

    companion object {
        val empty = AddressEntity("", "")
    }
}