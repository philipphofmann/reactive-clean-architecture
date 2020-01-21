package com.muffls.common.business

import kotlinx.coroutines.flow.Flow

interface Repository<T> {

    val all: Flow<List<T>>

    suspend fun save(item: T)
}
