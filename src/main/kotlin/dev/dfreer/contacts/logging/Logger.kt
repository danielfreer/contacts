package dev.dfreer.contacts.logging

interface Logger {
    fun error(name: String, exception: Exception? = null, message: () -> Any?)
    fun warn(name: String, exception: Exception? = null, message: () -> Any?)
    fun info(name: String, message: () -> Any?)
    fun debug(name: String, message: () -> Any?)
    fun trace(name: String, message: () -> Any?)
}
