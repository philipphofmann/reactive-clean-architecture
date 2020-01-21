package com.muffls.tap.main.business.domain

import com.muffls.tap.main.business.entities.Tap
import com.muffls.time.CurrentTime
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import java.util.UUID

class InMemoryTapRepository : TapRepository {

    private val subject = ConflatedBroadcastChannel<List<Tap>>()

    init {
        subject.sendBlocking(emptyList())
    }

    private var items: List<Tap> = ArrayList()
        private set(value) {
            field = value
            subject.sendBlocking(value)
        }

    override val tapCount: Flow<Long>
        get() = all.map { items -> items.size.toLong() }

    override val all: Flow<List<Tap>>
        get() = subject.asFlow()

    override suspend fun save(item: Tap) {
        val mutableItems = items.toMutableList()
        mutableItems.addAll(listOf(item))
        items = mutableItems
    }

    suspend fun save() {
        save(Tap(UUID.randomUUID().toString(), CurrentTime.nowZonedDateTime))
    }
}
