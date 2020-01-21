package com.muffls.tap.main.repository

import com.muffls.tap.main.business.domain.TapRepository
import com.muffls.tap.main.business.entities.Tap
import com.muffls.tap.main.repository.db.TapDao
import com.muffls.tap.main.repository.db.TapEntityMapper.mapToDomain
import com.muffls.tap.main.repository.db.TapEntityMapper.mapToEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultTapRepository @Inject constructor(
    private val tapDao: TapDao
) : TapRepository {

    override val tapCount: Flow<Long>
        get() {
            return tapDao.getTapCount()
        }

    override val all: Flow<List<Tap>>
        get() {
            return tapDao.getAll().map { mapToDomain(it) }
        }

    override suspend fun save(item: Tap) {
        return tapDao.save(mapToEntity(item))
    }
}
