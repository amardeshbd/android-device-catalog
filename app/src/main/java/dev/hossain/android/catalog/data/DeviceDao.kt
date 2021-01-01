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
    suspend fun insertAll(vararg devices: Device)

    @Insert
    suspend fun insert(device: Device)

    @Insert
    fun insertSync(device: Device)

    @Insert
    suspend fun insert(density: ScreenDensity)

    @Insert
    suspend fun insert(density: OpenGLVersion)

    @Insert
    suspend fun insert(abiVersion: AbiVersion)

    @Insert
    fun insertSync(abiVersion: AbiVersion)

    @Insert
    suspend fun insert(screenSize: ScreenSize)

    @Insert
    suspend fun insert(sdkVersion: SdkVersion)

    @Delete
    suspend fun delete(device: Device)

    @Delete
    fun deleteSync(device: Device)

    //
    // Relational queries
    //

    @Transaction
    @Query("SELECT * FROM device")
    fun getAllDeviceInfo(): List<DeviceInfo>
}
