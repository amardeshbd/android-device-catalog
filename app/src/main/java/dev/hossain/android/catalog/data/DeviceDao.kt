package dev.hossain.android.catalog.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import dev.hossain.android.catalog.data.model.AbiVersion
import dev.hossain.android.catalog.data.model.Device
import dev.hossain.android.catalog.data.model.DeviceInfo
import dev.hossain.android.catalog.data.model.OpenGLVersion
import dev.hossain.android.catalog.data.model.ScreenDensity
import dev.hossain.android.catalog.data.model.ScreenSize
import dev.hossain.android.catalog.data.model.SdkVersion

@Dao
interface DeviceDao {
    @Query("SELECT * FROM device")
    fun getAll(): List<Device>

    @Query("SELECT * FROM device WHERE _id IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<Device>

    //
    // Insert new data and relational data
    //

    @Insert
    fun insertAll(vararg devices: Device)

    @Insert
    fun insert(device: Device)

    @Insert
    fun insert(density: ScreenDensity)

    @Insert
    fun insert(density: OpenGLVersion)

    @Insert
    fun insert(abiVersion: AbiVersion)

    @Insert
    fun insert(screenSize: ScreenSize)

    @Insert
    fun insert(sdkVersion: SdkVersion)

    @Delete
    fun delete(device: Device)

    //
    // Relational queries
    //

    @Transaction
    @Query("SELECT * FROM device")
    fun getAllDeviceInfo(): List<DeviceInfo>
}
