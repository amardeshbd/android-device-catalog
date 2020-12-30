package dev.hossain.android.catalog.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity

/**
 * Different Android devices use different CPUs, which in turn support different instruction sets.
 * Each combination of CPU and instruction set has its own Application Binary Interface (ABI).
 *
 * Examples: arm64-v8a, armeabi, armeabi-v7a
 * See https://developer.android.com/ndk/guides/abis
 *
 * @see Device
 * @see DeviceInfo
 */
@Entity(tableName = "abi_versions", primaryKeys = ["abi", "device_id"])
data class AbiVersion(
    @ColumnInfo(name = "abi")
    val abi: String,
    @ColumnInfo(name = "device_id")
    val deviceId: Long
)
