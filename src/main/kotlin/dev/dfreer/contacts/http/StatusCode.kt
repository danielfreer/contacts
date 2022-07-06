package dev.dfreer.contacts.http

/** https://datatracker.ietf.org/doc/html/rfc7231 */
enum class StatusCode(val code: Int) {
    OK(200),
    CREATED(201),
    ACCEPTED(202),
    NO_CONTENT(204),

    BAD_REQUEST(400),
    NOT_FOUND(404),
    CONFLICT(409),

    INTERNAL_SERVER_ERROR(500),
}
