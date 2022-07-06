package dev.dfreer.contacts.json

import dev.dfreer.contacts.api.v1.*
import dev.dfreer.contacts.api.v1.Phone.Type.HOME
import dev.dfreer.contacts.api.v1.Phone.Type.MOBILE
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class ParserTest {
    @Test
    fun `should decode contact json`() = assertEquals(
        expected = contact,
        actual = Parser().decodeFromString(contactJson),
    )

    @Test
    fun `should encode contact`() = assertEquals(
        expected = contactJson,
        actual = Parser().encodeToString(contact),
    )

    @Test
    fun `should decode contact with id json`() = assertEquals(
        expected = contactWithId,
        actual = Parser().decodeFromString(contactWithIdJson),
    )

    @Test
    fun `should encode contact with id`() = assertEquals(
        expected = contactWithIdJson,
        actual = Parser().encodeToString(contactWithId),
    )

    @Test
    fun `when json is malformed, should throw an exception`() {
        assertFails { Parser().decodeFromString<Any>("{]") }
    }
}

private val contact = Contact(
    Name(first = "Harold", middle = "Francis", last = "Gilkey"),
    Address(street = "8360 High Autumn Row", city = "Cannon", state = "Delaware", zip = "19797"),
    phones = listOf(
        Phone(number = "302-611-9148", type = HOME),
        Phone(number = "302-532-9427", type = MOBILE),
    ),
    email = "harold.gilkey@yahoo.com",
)

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

private val contactWithId = ContactWithId(
    id = 101,
    Name(first = "Harold", middle = "Francis", last = "Gilkey"),
    Address(street = "8360 High Autumn Row", city = "Cannon", state = "Delaware", zip = "19797"),
    phones = listOf(
        Phone(number = "302-611-9148", type = HOME),
        Phone(number = "302-532-9427", type = MOBILE),
    ),
    email = "harold.gilkey@yahoo.com",
)

private val contactWithIdJson = """
{
    "id": 101,
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
