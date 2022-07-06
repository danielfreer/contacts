package dev.dfreer.contacts.logging

class NamedLoggerDecorator(private val logger: Logger, private val name: String) : NamedLogger {
    override fun error(exception: Exception?, message: () -> Any?) = logger.error(name, exception, message)
    override fun warn(exception: Exception?, message: () -> Any?) = logger.warn(name, exception, message)
    override fun info(message: () -> Any?) = logger.info(name, message)
    override fun debug(message: () -> Any?) = logger.debug(name, message)
    override fun trace(message: () -> Any?) = logger.trace(name, message)
}
