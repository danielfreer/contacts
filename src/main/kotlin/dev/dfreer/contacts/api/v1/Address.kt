package dev.dfreer.contacts.api.v1

import kotlinx.serialization.Serializable

@Serializable
data class Address (
    val street: String,
    val city: String,
    val state: String,
    val zip: String,
)
