package dev.dfreer.contacts.api.v1

data class Contact(
    val id: Int,
    val name: Name,
    val address: Address,
    val phones: List<Phone>,
    val email: String,
)
