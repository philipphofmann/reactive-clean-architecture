package com.muffls.monitoring

import org.junit.Assert.assertEquals
import org.junit.Test

class VerbosityTests {

    @Test
    fun `check the ordinal order of Verbosity`() {
        assertOrdinal(0, Log.Verbosity.DEBUG)
        assertOrdinal(1, Log.Verbosity.INFO)
        assertOrdinal(2, Log.Verbosity.WARN)
    }

    private fun assertOrdinal(expectedOrdinal: Int, verbosity: Log.Verbosity) {
        assertEquals(
            "The order of the values in Verbosity is wrong.",
            expectedOrdinal,
            verbosity.ordinal
        )
    }
}
