package dev.dfreer.contacts.json

import kotlin.test.Test
import kotlin.test.assertEquals

class ParserTest {
    @Test
    fun `should decode contact entry request json`() = assertEquals(
        expected = contactEntryRequest,
        actual = Parser().decodeFromString(contactEntryRequestJson),
    )

    @Test
    fun `should encode contact entry request`() = assertEquals(
        expected = contactEntryRequestJson,
        actual = Parser().encodeToString(contactEntryRequest),
    )

    @Test
    fun `should decode contact entry response json`() = assertEquals(
        expected = contactEntryResponse,
        actual = Parser().decodeFromString(contactEntryResponseJson),
    )

    @Test
    fun `should encode contact entry response`() = assertEquals(
        expected = contactEntryResponseJson,
        actual = Parser().encodeToString(contactEntryResponse),
    )
}

private val contactEntryRequest = ContactEntryRequest(
    Name(first = "Harold", middle = "Francis", last = "Gilkey"),
    Address(street = "8360 High Autumn Row", city = "Cannon", state = "Delaware", zip = "19797"),
    phones = listOf(
        Phone(number = "302-611-9148", type = Phone.Type.HOME),
        Phone(number = "302-532-9427", type = Phone.Type.MOBILE),
    ),
    email = "harold.gilkey@yahoo.com",
)

private val contactEntryRequestJson = """
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

private val contactEntryResponse = ContactEntryResponse(
    id = 101,
    Name(first = "Harold", middle = "Francis", last = "Gilkey"),
    Address(street = "8360 High Autumn Row", city = "Cannon", state = "Delaware", zip = "19797"),
    phones = listOf(
        Phone(number = "302-611-9148", type = Phone.Type.HOME),
        Phone(number = "302-532-9427", type = Phone.Type.MOBILE),
    ),
    email = "harold.gilkey@yahoo.com",
)

private val contactEntryResponseJson = """
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
