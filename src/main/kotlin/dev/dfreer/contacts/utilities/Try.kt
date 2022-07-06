package dev.dfreer.contacts.utilities

sealed interface Try<T>
data class Success<T>(val value: T) : Try<T>
data class Failure<T>(val exception: Exception) : Try<T>

inline fun <T> tryDoing(something: () -> T): Try<T> = try {
    Success(something())
} catch (exception: Exception) {
    Failure(exception)
}
