package dev.dfreer.contacts.logging

import java.io.PrintWriter
import java.io.StringWriter
import java.time.Instant

class BasicConsoleLogger : Logger {
    override fun error(name: String, exception: Exception?, message: () -> Any?) =
        write(name, logType = "ERROR", exception, message)

    override fun warn(name: String, exception: Exception?, message: () -> Any?) =
        write(name, logType = "WARN", exception, message)

    override fun info(name: String, message: () -> Any?) =
        write(name, logType = "INFO", exception = null, message)

    override fun debug(name: String, message: () -> Any?) =
        write(name, logType = "DEBUG", exception = null, message)

    override fun trace(name: String, message: () -> Any?) =
        write(name, logType = "TRACE", exception = null, message)

    private fun write(name: String, logType: String, exception: Exception?, message: () -> Any?) {
        val line = "${Instant.now()} [${Thread.currentThread().name}] $logType $name - ${message()}"

        StringWriter().use { stringWriter ->
            stringWriter.write(line)
            if (exception != null) {
                stringWriter.write(System.lineSeparator())
                PrintWriter(stringWriter).use { printWriter ->
                    exception.printStackTrace(printWriter)
                }
            }
            stringWriter.toString().let(::println)
        }
    }
}
