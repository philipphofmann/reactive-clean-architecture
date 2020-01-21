package com.muffls.common.business

import java.util.UUID
import javax.inject.Inject

@Suppress("UseDataClass")
class UUIDGenerator @Inject constructor() {
    val randomUUID: UUID
        get() {
            return UUID.randomUUID()
        }
}
