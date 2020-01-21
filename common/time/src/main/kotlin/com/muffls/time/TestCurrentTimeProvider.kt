package com.muffls.time

import org.threeten.bp.Clock
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.Month
import org.threeten.bp.ZoneOffset
import org.threeten.bp.ZonedDateTime

class TestCurrentTimeProvider(
    nowInstant: Instant = NOW.toInstant(),
    timeZoneOffset: Int = TIME_ZONE_OFFSET_IN_SECONDS
) : CurrentTimeProvider {

    private var _clock = Clock.fixed(nowInstant, ZoneOffset.ofTotalSeconds(timeZoneOffset))

    override var clock: Clock
        get() = _clock
        set(value) {
            _clock = value
        }

    companion object {
        const val TIME_ZONE_OFFSET_IN_SECONDS = 3_600
        val ZONE_OFFSET: ZoneOffset = ZoneOffset.ofTotalSeconds(TIME_ZONE_OFFSET_IN_SECONDS)
        val LOCAL_DATE_TIME_TODAY: LocalDateTime =
            LocalDateTime.of(2_019, Month.MARCH, 8, 12, 0)
        val NOW: ZonedDateTime = ZonedDateTime.of(LOCAL_DATE_TIME_TODAY, ZONE_OFFSET)

        fun init() {
            CurrentTime.init(TestCurrentTimeProvider())
        }
    }
}
