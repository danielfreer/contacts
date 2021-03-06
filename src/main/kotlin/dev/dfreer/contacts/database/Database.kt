package dev.dfreer.contacts.database

import dev.dfreer.contacts.api.v1.Contact
import dev.dfreer.contacts.api.v1.ContactWithId
import dev.dfreer.contacts.api.v1.Id

class Database(private val idGenerator: IdGenerator) {
    private val contactsById = mutableMapOf<Id, Contact>()

    fun create(contact: Contact): Id {
        val id = idGenerator.generate()
        contactsById[id] = contact
        return id
    }

    fun read(): List<ContactWithId> = contactsById.map { (id, contact) ->
        with(contact) { ContactWithId(id, name, address, phones, email) }
    }

    fun read(id: Id): ContactWithId? = contactsById[id]?.let { contact ->
        with(contact) { ContactWithId(id, name, address, phones, email) }
    }

    fun update(id: Id, contact: Contact): Boolean {
        if (contactsById.remove(id) == null) return false
        contactsById[id] = contact
        return true
    }

    fun delete(id: Id): Boolean = contactsById.remove(id) != null
}
