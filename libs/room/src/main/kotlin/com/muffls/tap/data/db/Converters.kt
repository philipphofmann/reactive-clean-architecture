package com.muffls.tap.data.db

import androidx.room.TypeConverter
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

class Converters {

    private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    @TypeConverter
    fun fromIsoOffsetDateTime(value: String): ZonedDateTime {
        return ZonedDateTime.parse(value, formatter)
    }

    @TypeConverter
    fun toIsoOffsetDateTime(zonedDateTime: ZonedDateTime): String {
        return zonedDateTime.format(formatter)
    }
}
