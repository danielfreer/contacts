package dev.dfreer.contacts.json

import kotlinx.serialization.Serializable

@Serializable
data class Name(
    val first: String,
    val middle: String,
    val last: String,
)
