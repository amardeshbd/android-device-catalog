package dev.hossain.android.catalog.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity

/**
 * Device supported API levels, also known as SDK version.
 * https://developer.android.com/reference/android/os/Build.VERSION_CODES
 *
 * Examples: 23, 24, 27
 *
 * @see Device
 * @see DeviceInfo
 */
@Entity(tableName = "sdk_version", primaryKeys = ["api_level", "device_id"])
data class SdkVersion(
    @ColumnInfo(name = "api_level")
    val apiLevel: Int,
    @ColumnInfo(name = "device_id")
    val deviceId: Long
)
