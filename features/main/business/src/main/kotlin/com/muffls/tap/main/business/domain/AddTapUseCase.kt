package com.muffls.tap.main.business.domain

import com.muffls.common.business.UUIDGenerator
import com.muffls.tap.main.business.entities.Tap
import com.muffls.time.CurrentTime
import javax.inject.Inject

class AddTapUseCase @Inject constructor(
    private val tapRepository: TapRepository,
    private val uuidGenerator: UUIDGenerator
) {

    suspend fun addTap() =
        tapRepository.save(
            Tap(uuidGenerator.randomUUID.toString(), CurrentTime.nowZonedDateTime)
        )
}
