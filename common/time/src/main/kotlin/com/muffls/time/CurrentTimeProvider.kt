package com.muffls.time

import org.threeten.bp.Clock
import org.threeten.bp.Instant
import org.threeten.bp.ZonedDateTime

interface CurrentTimeProvider {
    val clock: Clock
        get() = Clock.systemDefaultZone()
}

/**
 * Needed for dagger and java.
 */
open class CurrentTimeProviderImpl : CurrentTimeProvider

object CurrentTime {

    private lateinit var provider: CurrentTimeProvider

    @JvmStatic
    fun init(provider: CurrentTimeProvider) {
        CurrentTime.provider = provider
    }

    @JvmStatic
    val clock
        get() = provider.clock

    @JvmStatic
    val nowInstant: Instant
        get() = Instant.now(clock)

    @JvmStatic
    val nowZonedDateTime: ZonedDateTime
        get() = nowInstant.atZone(clock.zone)
}
