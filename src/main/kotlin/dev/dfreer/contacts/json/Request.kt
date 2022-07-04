package dev.dfreer.contacts.json

import dev.dfreer.contacts.api.v1.Address
import dev.dfreer.contacts.api.v1.Contact
import dev.dfreer.contacts.api.v1.Name
import dev.dfreer.contacts.api.v1.Phone
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Request(
    override val name: Name,
    override val address: Address,
    @SerialName("phone") override val phones: List<Phone>,
    override val email: String,
): Contact
