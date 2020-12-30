package dev.hossain.android.catalog.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "screen_density", primaryKeys = ["density", "device_id"])
data class ScreenDensity(
    @ColumnInfo(name = "density")
    val density: Int,
    @ColumnInfo(name = "device_id")
    val deviceId: Long
)