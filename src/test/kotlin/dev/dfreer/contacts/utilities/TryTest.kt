package dev.dfreer.contacts.utilities

import kotlin.test.Test
import kotlin.test.assertIs

class TryTest {
    @Test
    fun `when trying to do something succeeds, should return a success`() {
        assertIs<Success<Something>>(
            tryDoing { Something }
        )
    }

    @Test
    fun `when trying to do something fails, should return a failure`() {
        assertIs<Failure<Something>>(
            tryDoing { throw IllegalStateException("Failed to do something") }
        )
    }
}
