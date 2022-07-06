package dev.dfreer.contacts.http

import dev.dfreer.contacts.ContactsService
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.request.receiveText
import io.ktor.server.response.respondBytes
import io.ktor.server.routing.*

class Server(private val contactsService: ContactsService) {
    fun start(host: String, port: Int) {
        embeddedServer(
            Netty,
            port,
            host,
        ) { configureRouting(contactsService) }
            .start(wait = true)
    }

    private fun Application.configureRouting(contactsService: ContactsService) {
        routing {
            post("/contacts") {
                val json = call.receiveText()
                val response = contactsService.onCreate(json)
                call.respond(response)
            }
            get("/contacts") {
                val response = contactsService.onRead()
                call.respond(response)
            }
            get("/contacts/{id}") {
                val id = call.parameters["id"]
                val response = contactsService.onRead(id)
                call.respond(response)
            }
            put("/contacts/{id}") {
                val id = call.parameters["id"]
                val json = call.receiveText()
                val response = contactsService.onUpdate(id, json)
                call.respond(response)
            }
            delete("/contacts/{id}") {
                val id = call.parameters["id"]
                val response = contactsService.onDelete(id)
                call.respond(response)
            }
        }
    }
}

private suspend fun ApplicationCall.respond(response: Response) = when (response) {
    is Status -> this.response.status(response.statusCode.toHttpStatusCode())
    is StatusWithJson -> {
        respondBytes(ContentType.Application.Json, response.statusCode.toHttpStatusCode()) {
            response.json.toByteArray()
        }
    }
}

private fun StatusCode.toHttpStatusCode() = HttpStatusCode.fromValue(code)
