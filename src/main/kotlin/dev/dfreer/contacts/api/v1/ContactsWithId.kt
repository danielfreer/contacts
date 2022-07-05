package dev.dfreer.contacts.api.v1

import kotlinx.serialization.Serializable

@Serializable
data class ContactsWithId(val contactsWithId: List<ContactsWithId>)
