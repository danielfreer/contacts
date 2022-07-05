package dev.dfreer.contacts.utilities

sealed interface Either<LEFT, RIGHT>
data class Left<LEFT, RIGHT>(val value: LEFT) : Either<LEFT, RIGHT>
data class Right<LEFT, RIGHT>(val value: RIGHT) : Either<LEFT, RIGHT>

fun <T> Try<T>.toEither(): Either<Problem, T> = when (this) {
    is Success -> Right(value)
    is Failure -> Left(problem)
}
