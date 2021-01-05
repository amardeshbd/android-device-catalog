package dev.hossain.android.catalog.data.model

import androidx.room.ColumnInfo

/**
 * Provides unique manufacturer and count of devices
 *
 * https://developer.android.com/training/data-storage/room/accessing-data#return-subset
 */
data class TupleManufacturer(
    @ColumnInfo(name = "manufacturer") val manufacturerName: String,
    @ColumnInfo(name = "total_devices_made") val totalDevicesMade: String
)