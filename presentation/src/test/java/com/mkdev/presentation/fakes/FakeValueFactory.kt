package com.mkdev.presentation.fakes

import java.util.UUID
import kotlin.random.Random

object FakeValueFactory {

    fun randomString(): String {
        return UUID.randomUUID().toString()
    }

    fun randomInt(): Int {
        return Random.nextInt()
    }

    fun randomDouble(): Double {
        return Random.nextDouble()
    }

    fun randomBoolean(): Boolean {
        return Random.nextBoolean()
    }
}
