package dev.dfreer.contacts.json

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Parser {
    private val json = Json { prettyPrint = true }

    inline fun <reified T> decodeFromString(json: String): T = Json.decodeFromString(json)
    fun encodeToString(contactEntryRequest: ContactEntryRequest) = json.encodeToString(contactEntryRequest)
    fun encodeToString(contactEntryResponse: ContactEntryResponse) = json.encodeToString(contactEntryResponse)
}
