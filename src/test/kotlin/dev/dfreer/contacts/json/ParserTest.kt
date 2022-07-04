package dev.dfreer.contacts.json

import dev.dfreer.contacts.api.v1.Address
import dev.dfreer.contacts.api.v1.Name
import dev.dfreer.contacts.api.v1.Phone
import dev.dfreer.contacts.api.v1.Phone.Type.HOME
import dev.dfreer.contacts.api.v1.Phone.Type.MOBILE
import kotlin.test.Test
import kotlin.test.assertEquals

class ParserTest {
    @Test
    fun `should decode request json`() = assertEquals(
        expected = request,
        actual = Parser().decodeFromString(requestJson),
    )

    @Test
    fun `should encode request`() = assertEquals(
        expected = requestJson,
        actual = Parser().encodeToString(request),
    )

    @Test
    fun `should decode response json`() = assertEquals(
        expected = response,
        actual = Parser().decodeFromString(responseJson),
    )

    @Test
    fun `should encode response`() = assertEquals(
        expected = responseJson,
        actual = Parser().encodeToString(response),
    )
}

private val request = Request(
    Name(first = "Harold", middle = "Francis", last = "Gilkey"),
    Address(street = "8360 High Autumn Row", city = "Cannon", state = "Delaware", zip = "19797"),
    phones = listOf(
        Phone(number = "302-611-9148", type = HOME),
        Phone(number = "302-532-9427", type = MOBILE),
    ),
    email = "harold.gilkey@yahoo.com",
)

private val requestJson = """
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

private val response = Response(
    id = 101,
    Name(first = "Harold", middle = "Francis", last = "Gilkey"),
    Address(street = "8360 High Autumn Row", city = "Cannon", state = "Delaware", zip = "19797"),
    phones = listOf(
        Phone(number = "302-611-9148", type = HOME),
        Phone(number = "302-532-9427", type = MOBILE),
    ),
    email = "harold.gilkey@yahoo.com",
)

private val responseJson = """
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
