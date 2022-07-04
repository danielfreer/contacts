package dev.dfreer.contacts.api.v1

import kotlinx.serialization.Serializable

@Serializable
data class Name(
    val first: String,
    val middle: String,
    val last: String,
)
