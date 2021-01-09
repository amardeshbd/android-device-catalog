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
import dev.hossain.android.catalog.data.model.TupleManufacturer

@Dao
interface DeviceDao {
    @Query("SELECT * FROM devices")
    fun getAll(): List<Device>

    @Query("SELECT * FROM devices WHERE _id IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<Device>

    //
    // Select stats based queries
    //

    @Query("SELECT manufacturer, COUNT(*) AS total_devices_made FROM devices GROUP BY manufacturer")
    suspend fun getManufacturers(): List<TupleManufacturer>

    // Refs on using
    // https://stackoverflow.com/questions/55297165/room-dao-order-by-asc-or-desc-variable
    // https://stackoverflow.com/questions/44184769/android-room-select-query-with-like

    //
    // Insert new data and relational data
    //

    @Insert
    suspend fun insertAll(vararg devices: Device)

    @Insert
    suspend fun insert(device: Device): Long

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
    @Query("SELECT * FROM devices")
    fun getAllDeviceInfo(): List<DeviceInfo>

    @Transaction
    @Query("SELECT * FROM devices WHERE manufacturer=:manufacturerName")
    fun getManufacturerDevices(manufacturerName: String): List<DeviceInfo>
}
