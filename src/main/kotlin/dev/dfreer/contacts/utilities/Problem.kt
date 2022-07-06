package dev.dfreer.contacts.utilities

data class Problem(val message: String?, val exception: Exception?) {
    constructor(message: String) : this(message, exception = null)
}
