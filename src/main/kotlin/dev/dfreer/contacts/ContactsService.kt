package dev.dfreer.contacts

import dev.dfreer.contacts.api.v1.Contact
import dev.dfreer.contacts.database.Database
import dev.dfreer.contacts.http.Response
import dev.dfreer.contacts.http.Status
import dev.dfreer.contacts.http.StatusCode.*
import dev.dfreer.contacts.http.StatusWithJson
import dev.dfreer.contacts.json.Parser
import dev.dfreer.contacts.logging.Logger
import dev.dfreer.contacts.logging.named
import dev.dfreer.contacts.utilities.noteFalse
import dev.dfreer.contacts.utilities.noteNull
import dev.dfreer.contacts.utilities.toEither
import dev.dfreer.contacts.utilities.tryDoing

class ContactsService(
    private val parser: Parser,
    private val database: Database,
    baseLogger: Logger,
) {
    private val logger = baseLogger.named {}

    fun onCreate(json: String): Response {
        return tryDoing { parser.decodeFromString<Contact>(json) }
            .toEither { exception ->
                logger.error(exception) { "Failed to parse contact from json: $json" }
                Status(BAD_REQUEST)
            }
            .flatMap { contact ->
                val id = database.create(contact)
                val contactWithId = database.read(id)
                contactWithId.noteNull {
                    logger.error { "Failed to read contact with id=$id: $contact" }
                    Status(INTERNAL_SERVER_ERROR)
                }
            }
            .map(parser::encodeToString)
            .map<Response> { StatusWithJson(CREATED, it) }
            .recover { it }
    }

    fun onRead(): Response {
        return database.read()
            .let(parser::encodeToString)
            .let { json -> StatusWithJson(OK, json) }
    }

    fun onRead(idString: String?): Response {
        return idString
            .noteNull {
                logger.error { "Id cannot be null" }
                Status(BAD_REQUEST)
            }
            .flatMap { id ->
                tryDoing { id.toLong() }
                    .toEither { exception ->
                        logger.error(exception) { "Invalid id: $id" }
                        Status(BAD_REQUEST)
                    }
            }
            .flatMap { id ->
                database.read(id).noteNull {
                    logger.error { "Unknown id: $id" }
                    Status(NOT_FOUND)
                }
            }
            .map(parser::encodeToString)
            .map<Response> { json -> StatusWithJson(OK, json) }
            .recover { it }
    }

    fun onUpdate(idString: String?, json: String): Response {
        return idString
            .noteNull {
                logger.error { "Id cannot be null" }
                Status(BAD_REQUEST)
            }
            .flatMap { id ->
                tryDoing { id.toLong() }
                    .toEither { exception ->
                        logger.error(exception) { "Invalid id: $id" }
                        Status(BAD_REQUEST)
                    }
            }
            .flatMap { id ->
                tryDoing { parser.decodeFromString<Contact>(json) }
                    .toEither { exception ->
                        logger.error(exception) { "Failed to parse contact from json: $json" }
                        Status(BAD_REQUEST)
                    }
                    .map { contact -> id to contact }
            }
            .flatMap { (id, contact) ->
                database.update(id, contact).noteFalse {
                    logger.error { "Unknown id: $id" }
                    Status(NOT_FOUND)
                }
            }
            .map { Status(NO_CONTENT) }
            .recover { it }
    }

    fun onDelete(idString: String?): Response {
        return idString
            .noteNull {
                logger.error { "Id cannot be null" }
                Status(BAD_REQUEST)
            }
            .flatMap { id ->
                tryDoing { id.toLong() }
                    .toEither { exception ->
                        logger.error(exception) { "Invalid id: $id" }
                        Status(BAD_REQUEST)
                    }
            }
            .flatMap { id ->
                database.delete(id).noteFalse {
                    logger.error { "Unknown id: $id" }
                    Status(NOT_FOUND)
                }
            }
            .map { Status(NO_CONTENT) }
            .recover { it }
    }
}
