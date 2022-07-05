package dev.dfreer.contacts.database

import dev.dfreer.contacts.api.v1.*
import kotlin.test.*

class DatabaseTest {
    private val contact = testContact("daniel")
    private val updatedContact = testContact("jared")
    private lateinit var database: Database

    @BeforeTest
    private fun createDatabase() { database = Database(IdGenerator()) }

    @Test
    fun `when creating a contact, should return an id`() {
        assertNotNull(database.create(contact))
    }

    @Test
    fun `given a contact has been created, when reading the id, should return the contact`() {
        val id = database.create(contact)
        assertEquals(
            expected = contact.withId(0),
            actual = database.read(id),
        )
    }

    @Test
    fun `given contacts have been created, when reading, should return contacts`() {
        database.create(contact)
        database.create(updatedContact)
        assertContentEquals(
            expected = listOf(contact.withId(0), updatedContact.withId(1)),
            actual = database.read(),
        )
    }

    @Test
    fun `given a contact has been created, when updating the contact by id, should return true`() {
        val id = database.create(contact)
        assertTrue(database.update(id, updatedContact))
    }

    @Test
    fun `given a contact has been created and updated, when reading the id, should return the updated contact`() {
        val id = database.create(contact)
        database.update(id, updatedContact)
        assertEquals(
            expected = updatedContact.withId(0),
            actual = database.read(id),
        )
    }

    @Test
    fun `given a contact has been created, when deleting the id, should return true`() {
        val id = database.create(contact)
        assertTrue(database.delete(id))
    }

    @Test
    fun `given a contact has been created and deleted, when reading the id, should return null`() {
        val id = database.create(contact)
        database.delete(id)
        assertNull(database.read(id))
    }

    @Test
    fun `when reading an unknown id, should return null`() {
        assertNull(database.read(1))
    }

    @Test
    fun `when reading an empty database, should return an empty list`() {
        assertContentEquals(
            expected = emptyList(),
            actual = database.read(),
        )
    }

    @Test
    fun `when updating an unknown id, should return false`() {
        assertFalse(database.update(1, updatedContact))
    }

    @Test
    fun `when deleting an unknown id, should return false`() {
        assertFalse(database.delete(1))
    }
}

private fun testContact(firstName: String) = Contact(
    Name(firstName, middle = "", last = ""),
    Address(street = "", city = "", state = "", zip = ""),
    phones = emptyList<Phone>(),
    email = "",
)
