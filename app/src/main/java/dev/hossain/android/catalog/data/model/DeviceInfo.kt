package dev.hossain.android.catalog.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

@Entity(tableName = "device_screen_density")
data class DeviceInfo(
    @Embedded
    val device: Device,

    @Relation(parentColumn = "_id", entityColumn = "device_id")
    val screenDensities: List<ScreenDensity>,

    @Relation(parentColumn = "_id", entityColumn = "device_id")
    val openglVersions: List<OpenGLVersion>
)
