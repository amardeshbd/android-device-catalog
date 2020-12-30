package dev.hossain.android.catalog.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.hossain.android.catalog.data.model.AbiVersion
import dev.hossain.android.catalog.data.model.Device
import dev.hossain.android.catalog.data.model.OpenGLVersion
import dev.hossain.android.catalog.data.model.ScreenDensity
import dev.hossain.android.catalog.data.model.ScreenSize
import dev.hossain.android.catalog.data.model.SdkVersion

@Database(
    entities = [
        Device::class,
        ScreenDensity::class,
        OpenGLVersion::class,
        ScreenSize::class,
        SdkVersion::class,
        AbiVersion::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(DataConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun deviceDao(): DeviceDao
}
