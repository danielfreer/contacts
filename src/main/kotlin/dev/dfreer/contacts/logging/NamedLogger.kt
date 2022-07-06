package dev.dfreer.contacts.logging

interface NamedLogger {
    fun error(exception: Exception? = null, message: () -> Any?)
    fun warn(exception: Exception? = null, message: () -> Any?)
    fun info(message: () -> Any?)
    fun debug(message: () -> Any?)
    fun trace(message: () -> Any?)
}

@Suppress("NOTHING_TO_INLINE")
inline fun Logger.named(noinline func: () -> Unit): NamedLogger = NamedLoggerDecorator(
    logger = this,
    name = func.javaClass.name.substringBefore("$")
)
