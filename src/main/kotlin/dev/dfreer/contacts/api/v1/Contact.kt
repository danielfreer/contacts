package dev.dfreer.contacts.api.v1

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Contact(
    val name: Name,
    val address: Address,
    @SerialName("phone") val phones: List<Phone>,
    val email: String,
)
