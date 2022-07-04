package dev.dfreer.contacts.api.v1

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Phone(
    val number: String,
    val type: Type,
) {
    @Serializable
    enum class Type {
        @SerialName("home") HOME,
        @SerialName("work") WORK,
        @SerialName("mobile") MOBILE,
    }
}
