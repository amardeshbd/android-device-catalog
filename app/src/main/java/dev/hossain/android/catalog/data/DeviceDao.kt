package dev.hossain.android.catalog.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

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

    @Delete
    fun delete(device: Device)
}