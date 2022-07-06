package dev.dfreer.contacts.http

sealed interface Response
data class Status(val statusCode: StatusCode) : Response
data class StatusWithJson(val statusCode: StatusCode, val json: String) : Response
