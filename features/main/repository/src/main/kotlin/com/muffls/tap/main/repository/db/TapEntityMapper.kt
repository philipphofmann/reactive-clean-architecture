package com.muffls.tap.main.repository.db

import com.muffls.tap.data.db.EntityMapper
import com.muffls.tap.main.business.entities.Tap

object TapEntityMapper : EntityMapper<TapEntity, Tap> {
    override fun mapToDomain(item: TapEntity) = Tap(item.id, item.zonedDateTime)
    override fun mapToDomain(items: List<TapEntity>) = items.map { mapToDomain(it) }
    override fun mapToEntity(item: Tap) = TapEntity(item.id, item.zonedDateTime)
    override fun mapToEntity(items: List<Tap>) = items.map { TapEntity(it.id, it.zonedDateTime) }
}
