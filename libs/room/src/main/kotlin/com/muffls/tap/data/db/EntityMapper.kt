package com.muffls.tap.data.db

interface EntityMapper<E, D> {

    fun mapToDomain(item: E): D

    fun mapToDomain(items: List<E>): List<D>

    fun mapToEntity(item: D): E

    fun mapToEntity(items: List<D>): List<E>
}
