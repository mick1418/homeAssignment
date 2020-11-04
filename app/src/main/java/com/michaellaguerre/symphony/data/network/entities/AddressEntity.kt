package com.michaellaguerre.symphony.data.network.entities

import com.michaellaguerre.symphony.domain.entities.Address

data class AddressEntity(
    val latitude: String,
    val longitude: String
) {

    /**
     * Conversion to domain entity.
     */
    fun toAddress() = Address(latitude.toDouble(), longitude.toDouble())

    companion object {
        val empty = AddressEntity("", "")
    }
}