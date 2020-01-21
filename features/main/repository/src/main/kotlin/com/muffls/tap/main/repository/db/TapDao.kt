package com.muffls.tap.main.repository.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TapDao {
    @Insert(onConflict = REPLACE)
    suspend fun save(tapEntity: TapEntity)

    @Query("SELECT * from TapEntity")
    fun getAll(): Flow<List<TapEntity>>

    @Query("SELECT count(*) from TapEntity")
    fun getTapCount(): Flow<Long>

    @Query("DELETE from TapEntity")
    suspend fun deleteAll()
}
