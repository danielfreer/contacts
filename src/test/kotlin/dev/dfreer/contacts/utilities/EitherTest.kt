package dev.dfreer.contacts.utilities

import kotlin.test.Test
import kotlin.test.assertIs

class EitherTest {
    @Test
    fun `given a successful try, should return a right`() {
        assertIs<Right<Exception, Something>>(
            Success(Something).toEither()
        )
    }

    @Test
    fun `given a failed try, should return a left`() {
        assertIs<Left<Exception, Something>>(
            Failure<Something>(IllegalArgumentException("")).toEither()
        )
    }
}
