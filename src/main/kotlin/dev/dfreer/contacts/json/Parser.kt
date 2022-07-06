package dev.dfreer.contacts.json

import dev.dfreer.contacts.api.v1.Contact
import dev.dfreer.contacts.api.v1.ContactWithId
import dev.dfreer.contacts.api.v1.ContactsWithId
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Parser {
    private val json = Json { prettyPrint = true }

    inline fun <reified T> decodeFromString(json: String): T = Json.decodeFromString(json)
    fun encodeToString(contact: Contact) = json.encodeToString(contact)
    fun encodeToString(contactWithId: ContactWithId) = json.encodeToString(contactWithId)
    fun encodeToString(contactsWithId: List<ContactsWithId>) = json.encodeToString(contactsWithId)
}
