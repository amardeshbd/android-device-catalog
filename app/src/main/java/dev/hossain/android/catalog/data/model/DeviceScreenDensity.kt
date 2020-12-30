package dev.hossain.android.catalog.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import dev.hossain.android.catalog.data.Device

@Entity(tableName = "device_screen_density")
data class DeviceScreenDensity(
    @Embedded
    val device: Device,

    @Relation(parentColumn = "_id", entityColumn = "device_id")
    val screenDensities: List<ScreenDensity>,

    @Relation(parentColumn = "_id", entityColumn = "device_id")
    val openglVersions: List<OpenGLVersion>
)