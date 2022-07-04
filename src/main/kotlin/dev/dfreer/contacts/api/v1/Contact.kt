package dev.dfreer.contacts.api.v1

interface Contact {
    val name: Name
    val address: Address
    val phones: List<Phone>
    val email: String
}
