package dev.hossain.android.catalog.data

import androidx.room.*
import dev.hossain.android.catalog.data.model.DeviceScreenDensity
import dev.hossain.android.catalog.data.model.OpenGLVersion
import dev.hossain.android.catalog.data.model.ScreenDensity

@Dao
interface DeviceDao {
    @Query("SELECT * FROM device")
    fun getAll(): List<Device>

    @Query("SELECT * FROM device WHERE _id IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<Device>

    @Insert
    fun insertAll(vararg devices: Device)

    @Insert
    fun insert(device: Device)

    @Insert
    fun insert(density: ScreenDensity)

    @Insert
    fun insert(density: OpenGLVersion)

    @Delete
    fun delete(device: Device)


    // Relational quries
    @Transaction
    @Query("SELECT * FROM device")
    fun getUsersAndLibraries(): List<DeviceScreenDensity>
}
