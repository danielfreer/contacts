package dev.dfreer.contacts.logging

import mu.KotlinLogging

class KotlinLoggingLogger : Logger {
    override fun error(name: String, exception: Exception?, message: () -> Any?) =
        KotlinLogging.logger(name).error(exception, message)

    override fun warn(name: String, exception: Exception?, message: () -> Any?) =
        KotlinLogging.logger(name).warn(exception, message)

    override fun info(name: String, message: () -> Any?) = KotlinLogging.logger(name).info(message)
    override fun debug(name: String, message: () -> Any?) = KotlinLogging.logger(name).debug(message)
    override fun trace(name: String, message: () -> Any?) = KotlinLogging.logger(name).trace(message)
}
