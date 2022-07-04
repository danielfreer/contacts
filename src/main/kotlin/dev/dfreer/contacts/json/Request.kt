package dev.dfreer.contacts.json

import dev.dfreer.contacts.api.v1.Address
import dev.dfreer.contacts.api.v1.Name
import dev.dfreer.contacts.api.v1.Phone
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Request (
    val name: Name,
    val address: Address,
    @SerialName("phone") val phones: List<Phone>,
    val email: String,
)
