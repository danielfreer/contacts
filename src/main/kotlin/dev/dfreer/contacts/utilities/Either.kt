package dev.dfreer.contacts.utilities

sealed class Either<LEFT, RIGHT> {
    inline fun <R> map(transform: (RIGHT) -> R): Either<LEFT, R> = when (this) {
        is Right -> Right(transform(value))
        is Left -> {
            @Suppress("UNCHECKED_CAST")
            this as Either<LEFT, R>
        }
    }

    inline fun <R> mapLeft(transform: (LEFT) -> R): Either<R, RIGHT> = when (this) {
        is Right -> {
            @Suppress("UNCHECKED_CAST")
            this as Either<R, RIGHT>
        }
        is Left -> Left(transform(value))
    }

    inline fun <R> flatMap(transform: (RIGHT) -> Either<LEFT, R>): Either<LEFT, R> = when (this) {
        is Right -> transform(value)
        is Left -> {
            @Suppress("UNCHECKED_CAST")
            this as Either<LEFT, R>
        }
    }

    inline fun recover(transform: (LEFT) -> RIGHT): RIGHT = when (this) {
        is Right -> value
        is Left -> transform(value)
    }
}

inline fun <LEFT, T> T?.noteNull(onNull: () -> LEFT): Either<LEFT, T> =
    if (this != null) Right(this) else Left(onNull())

inline fun <LEFT> Boolean.noteFalse(onFalse: () -> LEFT): Either<LEFT, Unit> =
    if (this) Right(Unit) else Left(onFalse())

data class Left<LEFT, RIGHT>(val value: LEFT) : Either<LEFT, RIGHT>()
data class Right<LEFT, RIGHT>(val value: RIGHT) : Either<LEFT, RIGHT>()

fun <T> Try<T>.toEither(): Either<Exception, T> = when (this) {
    is Success -> Right(value)
    is Failure -> Left(exception)
}

inline fun <LEFT, T> Try<T>.toEither(transform: (Exception) -> LEFT): Either<LEFT, T> = when (this) {
    is Success -> Right(value)
    is Failure -> Left(transform(exception))
}
