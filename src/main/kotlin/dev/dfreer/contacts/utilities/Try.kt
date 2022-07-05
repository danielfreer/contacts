package dev.dfreer.contacts.utilities

sealed interface Try<T>
data class Success<T>(val value: T) : Try<T>
data class Failure<T>(val problem: Problem) : Try<T>

inline fun <T> tryDoing(
    message: String? = null,
    something: () -> T
): Try<T> = try {
    Success(something())
} catch (e: Exception) {
    Failure(Problem(message, e))
}
