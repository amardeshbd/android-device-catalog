package dev.hossain.android.catalog.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity

/**
 * Device screen density. Also referred as MDPI, HDPI, XHDPI and so on.
 *
 * Examples: 160, 213, 240, 300, 320
 *
 * @see Device
 * @see DeviceInfo
 */
@Entity(tableName = "screen_density", primaryKeys = ["density", "device_id"])
data class ScreenDensity(
    @ColumnInfo(name = "density")
    val density: Int,
    @ColumnInfo(name = "device_id")
    val deviceId: Long
)
