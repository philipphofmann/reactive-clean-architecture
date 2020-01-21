package com.muffls.testing

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.Assert.assertEquals

suspend fun <T> Flow<T>.assertValue(expected: T) {
    val result = this.take(1).toList().first()
    assertEquals(expected, result)
}

fun <T> runBlockingWithTimeOut(timeMillis: Long = 500, block: suspend CoroutineScope.() -> T): T {
    return runBlocking { withTimeout(timeMillis) { block() } }
}