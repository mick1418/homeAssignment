package com.michaellaguerre.homeassignment.data.entities

import com.michaellaguerre.homeassignment.domain.entities.Address

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