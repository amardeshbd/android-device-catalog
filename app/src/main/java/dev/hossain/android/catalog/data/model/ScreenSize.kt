package dev.hossain.android.catalog.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity

/**
 * Device Screen size.
 *
 * Examples: 1024x600, 1366x768, 480x854, 800x1280
 *
 * @see Device
 * @see DeviceInfo
 */
@Entity(tableName = "screen_sizes", primaryKeys = ["screen_size", "device_id"])
data class ScreenSize(
    @ColumnInfo(name = "screen_size")
    val screenSize: String,
    @ColumnInfo(name = "device_id")
    val deviceId: Long
)
