package dev.hossain.android.catalog.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "opengl_versions", primaryKeys = ["version_number", "device_id"])
data class OpenGLVersion(
    @ColumnInfo(name = "version_number")
    val version: String,
    @ColumnInfo(name = "device_id")
    val deviceId: Long
)
