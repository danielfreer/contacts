package dev.dfreer.contacts.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContactEntryResponse (
    val id: Int,
    val name: Name,
    val address: Address,
    @SerialName("phone") val phones: List<Phone>,
    val email: String,
)
