package com.muffls.tap.main.business.domain

import com.muffls.common.business.Repository
import com.muffls.tap.main.business.entities.Tap
import kotlinx.coroutines.flow.Flow

interface TapRepository : Repository<Tap> {
    val tapCount: Flow<Long>
}
