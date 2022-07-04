package dev.dfreer.contacts.database

import dev.dfreer.contacts.api.v1.Id
import java.util.concurrent.atomic.AtomicLong

class IdGenerator {
    private val ids = AtomicLong()

    fun generate(): Id = ids.getAndIncrement()
}
