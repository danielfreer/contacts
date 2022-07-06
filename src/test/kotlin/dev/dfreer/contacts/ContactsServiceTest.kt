package dev.dfreer.contacts

import dev.dfreer.contacts.api.v1.Id
import dev.dfreer.contacts.database.Database
import dev.dfreer.contacts.database.IdGenerator
import dev.dfreer.contacts.http.Status
import dev.dfreer.contacts.http.StatusCode
import dev.dfreer.contacts.http.StatusCode.*
import dev.dfreer.contacts.http.StatusWithJson
import dev.dfreer.contacts.json.Parser
import dev.dfreer.contacts.logging.BasicConsoleLogger
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class ContactsServiceTest {
    private lateinit var contactsService: ContactsService

    @BeforeTest
    fun createContactsService() {
        contactsService = ContactsService(Parser(), Database(IdGenerator()), BasicConsoleLogger())
    }

    @Test
    fun `when a contact is created, should return the contact with id`() {
        val response = contactsService.onCreate(contactJson) as? StatusWithJson
        assertEquals(
            expected = contactWithIdJson,
            actual = response?.json,
        )
    }

    @Test
    fun `when a contact is created, should return a created status`() {
        val response = contactsService.onCreate(contactJson) as? StatusWithJson
        assertEquals(
            expected = CREATED,
            actual = response?.statusCode,
        )
    }

    @Test
    fun `when a contact is created with invalid json, should return a bad request status`() {
        val response = contactsService.onCreate("{]") as? Status
        assertEquals(
            expected = StatusCode.BAD_REQUEST,
            actual = response?.statusCode,
        )
    }

    @Test
    fun `given a contact, when the same contact is created again, should return a new id`() {
        val firstResponse = contactsService.onCreate(contactJson)
        val secondResponse = contactsService.onCreate(contactJson)
        assertNotEquals(
            firstResponse,
            secondResponse,
        )
    }

    @Test
    fun `given no existing contacts, when read, should return empty list`() {
        val response = contactsService.onRead() as? StatusWithJson
        assertEquals(
            expected = "[\n]",
            actual = response?.json,
        )
    }

    @Test
    fun `given some contacts, when read, should return a list of contacts`() {
        contactsService.onCreate(contactJson)
        contactsService.onCreate(contactJson)
        val response = contactsService.onRead() as? StatusWithJson
        assertEquals(
            expected = listOf(contactWithIdJson(0L), contactWithIdJson(1L)).prettyPrint(),
            actual = response?.json,
        )
    }

    @Test
    fun `when read, should return an ok status`() {
        val response = contactsService.onRead() as? StatusWithJson
        assertEquals(
            expected = OK,
            actual = response?.statusCode,
        )
    }

    @Test
    fun `given a contact, when read using an id, should return the contact`() {
        contactsService.onCreate(contactJson)
        val response = contactsService.onRead("0") as? StatusWithJson
        assertEquals(
            expected = contactWithIdJson,
            actual = response?.json,
        )
    }

    @Test
    fun `given a contact, when read using an id, should return a ok status`() {
        contactsService.onCreate(contactJson)
        val response = contactsService.onRead("0") as? StatusWithJson
        assertEquals(
            expected = OK,
            actual = response?.statusCode,
        )
    }

    @Test
    fun `when read using an unknown id, should return a not found status`() {
        val response = contactsService.onRead("42") as? Status
        assertEquals(
            expected = NOT_FOUND,
            actual = response?.statusCode,
        )
    }

    @Test
    fun `when read using an invalid id, should return a bad request status`() {
        val response = contactsService.onRead("ABC") as? Status
        assertEquals(
            expected = BAD_REQUEST,
            actual = response?.statusCode,
        )
    }

    @Test
    fun `given a contact that has been updated, when read using an id, should return the updated contact`() {
        contactsService.onCreate(contactJson)
        contactsService.onUpdate("0", updatedContactJson)
        val response = contactsService.onRead("0") as? StatusWithJson
        assertEquals(
            expected = updatedContactWithIdJson,
            actual = response?.json,
        )
    }

    @Test
    fun `given a contact, when updated using an id, should return a no content status`() {
        contactsService.onCreate(contactJson)
        val response = contactsService.onUpdate("0", updatedContactJson) as? Status
        assertEquals(
            expected = NO_CONTENT,
            actual = response?.statusCode,
        )
    }

    @Test
    fun `when updated using an unknown id, should return a not found status`() {
        val response = contactsService.onUpdate("42", updatedContactJson) as? Status
        assertEquals(
            expected = NOT_FOUND,
            actual = response?.statusCode
        )
    }

    @Test
    fun `when updated using an invalid id, should return a bad request status`() {
        val response = contactsService.onUpdate("ABC", updatedContactJson) as? Status
        assertEquals(
            expected = BAD_REQUEST,
            actual = response?.statusCode
        )
    }

    @Test
    fun `when updated using invalid json, should return a bad request status`() {
        contactsService.onCreate(contactJson)
        val response = contactsService.onUpdate("0", "{]") as? Status
        assertEquals(
            expected = BAD_REQUEST,
            actual = response?.statusCode
        )
    }

    @Test
    fun `given a contact, when updating with the same data, should return a no content status`() {
        contactsService.onCreate(contactJson)
        val response = contactsService.onUpdate("0", contactJson) as? Status
        assertEquals(
            expected = NO_CONTENT,
            actual = response?.statusCode,
        )
    }

    @Test
    fun `given a contact, when deleting using an id, should return a no content status`() {
        contactsService.onCreate(contactJson)
        val response = contactsService.onDelete("0") as? Status
        assertEquals(
            expected = NO_CONTENT,
            actual = response?.statusCode,
        )
    }

    @Test
    fun `when deleting using an unknown id, should return a not found status`() {
        val response = contactsService.onDelete("42") as? Status
        assertEquals(
            expected = NOT_FOUND,
            actual = response?.statusCode,
        )
    }

    @Test
    fun `when deleting using an invalid id, should return a bad request status`() {
        val response = contactsService.onDelete("abc") as? Status
        assertEquals(
            expected = BAD_REQUEST,
            actual = response?.statusCode,
        )
    }
}

private val contactJson = """
{
    "name": {
        "first": "Harold",
        "middle": "Francis",
        "last": "Gilkey"
    },
    "address": {
        "street": "8360 High Autumn Row",
        "city": "Cannon",
        "state": "Delaware",
        "zip": "19797"
    },
    "phone": [
        {
            "number": "302-611-9148",
            "type": "home"
        },
        {
            "number": "302-532-9427",
            "type": "mobile"
        }
    ],
    "email": "harold.gilkey@yahoo.com"
}
""".trimIndent()

private val contactWithIdJson = contactWithIdJson(id = 0)
private fun contactWithIdJson(id: Id) = """
{
    "id": $id,
    "name": {
        "first": "Harold",
        "middle": "Francis",
        "last": "Gilkey"
    },
    "address": {
        "street": "8360 High Autumn Row",
        "city": "Cannon",
        "state": "Delaware",
        "zip": "19797"
    },
    "phone": [
        {
            "number": "302-611-9148",
            "type": "home"
        },
        {
            "number": "302-532-9427",
            "type": "mobile"
        }
    ],
    "email": "harold.gilkey@yahoo.com"
}
""".trimIndent()

private val updatedContactJson = """
{
    "name": {
        "first": "Harold",
        "middle": "Francis",
        "last": "Gilkey"
    },
    "address": {
        "street": "8360 High Autumn Row",
        "city": "Cannon",
        "state": "Delaware",
        "zip": "19797"
    },
    "phone": [
        {
            "number": "1-302-611-9148",
            "type": "home"
        },
        {
            "number": "1-302-532-9427",
            "type": "mobile"
        }
    ],
    "email": "harold.gilkey@yahoo.com"
}
""".trimIndent()

private val updatedContactWithIdJson  = """
{
    "id": 0,
    "name": {
        "first": "Harold",
        "middle": "Francis",
        "last": "Gilkey"
    },
    "address": {
        "street": "8360 High Autumn Row",
        "city": "Cannon",
        "state": "Delaware",
        "zip": "19797"
    },
    "phone": [
        {
            "number": "1-302-611-9148",
            "type": "home"
        },
        {
            "number": "1-302-532-9427",
            "type": "mobile"
        }
    ],
    "email": "harold.gilkey@yahoo.com"
}
""".trimIndent()

private fun List<String>.prettyPrint() = joinToString(prefix = "[\n", separator = ",\n", postfix = "\n]") { json ->
    json.split('\n').joinToString(separator = "\n") { line -> "    $line" }
}
