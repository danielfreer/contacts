package dev.dfreer.contacts.json

import dev.dfreer.contacts.api.v1.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val id: Id,
    override val name: Name,
    override val address: Address,
    @SerialName("phone") override val phones: List<Phone>,
    override val email: String,
): Contact
