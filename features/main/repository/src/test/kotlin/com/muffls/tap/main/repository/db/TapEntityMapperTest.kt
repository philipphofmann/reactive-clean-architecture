package com.muffls.tap.main.repository.db

import com.muffls.tap.main.business.entities.Tap
import com.muffls.tap.main.repository.db.TapEntityMapper.mapToDomain
import com.muffls.tap.main.repository.db.TapEntityMapper.mapToEntity
import com.muffls.time.CurrentTime
import com.muffls.time.TestCurrentTimeProvider
import org.junit.Assert.assertEquals
import org.junit.Test

class TapEntityMapperTest {

    init {
        TestCurrentTimeProvider.init()
    }

    @Test
    fun `mapToDomain maps correctly`() {
        val actual = mapToDomain(tapEntity1)

        assertEquals(Tap(tapEntity1.id, tapEntity1.zonedDateTime), actual)
    }

    @Test
    fun `mapToDomain with multiple maps correctly`() {
        val actual = mapToDomain(listOf(tapEntity1, tapEntity2))

        val expected = listOf(tap1, tap2)
        assertEquals(expected, actual)
    }

    @Test
    fun `mapToEntity maps correctly`() {
        val actual = mapToEntity(tap1)

        assertEquals(tapEntity1, actual)
    }

    @Test
    fun `mapToEntity with multiple maps correctly`() {
        val actual = mapToEntity(listOf(tap1, tap2))

        assertEquals(2, actual.size)
        assertEquals(tapEntity1, actual[0])
        assertEquals(tapEntity2, actual[1])
    }

    private fun assertEquals(expected: TapEntity, actual: TapEntity) {
        assertEquals(expected.id, actual.id)
        assertEquals(expected.zonedDateTime, actual.zonedDateTime)
    }

    private val tapEntity1 = TapEntity("1", CurrentTime.nowZonedDateTime)
    private val tapEntity2 = TapEntity("2", CurrentTime.nowZonedDateTime)

    private val tap1 = Tap(tapEntity1.id, tapEntity1.zonedDateTime)
    private val tap2 = Tap(tapEntity2.id, tapEntity2.zonedDateTime)
}
