package com.muffls.common.business

import kotlinx.coroutines.flow.Flow

/**
 * It is guaranteed that all methods don't run on the [com.muffls.libs.rxjava2.SchedulerProvider.ui]
 * thread.
 */
interface Repository<T> {

    val all: Flow<List<T>>

    suspend fun save(item: T)
}
