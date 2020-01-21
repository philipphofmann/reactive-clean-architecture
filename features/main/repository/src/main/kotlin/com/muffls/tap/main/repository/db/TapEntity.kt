package com.muffls.tap.main.repository.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.ZonedDateTime

@Entity
data class TapEntity(@PrimaryKey val id: String, val zonedDateTime: ZonedDateTime)
