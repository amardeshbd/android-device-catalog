package dev.hossain.android.catalog.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Model class for representing an Android device from the catalog.
 */
@Entity
data class Device(
    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    /**
     * Device manufacturer name.
     * Examples: Acer, LG, Samsung
     */
    @ColumnInfo(name = "manufacturer", index = true)
    val manufacturer: String = "",

    /**
     * Device model name (usually public facing)
     * Examples: Iconia One 7, S13, ZenFone 4 (ZE554KL)
     */
    @ColumnInfo(name = "model_name", index = true)
    val modelName: String = "",

    /**
     * Device model code internally used by manufacturer
     * Examples: ASUS_Z01KD_3, K00X_1, BLU_NEO_X_LTE
     */
    @ColumnInfo(name = "model_code", index = true)
    val modelCode: String = "",

    /**
     * Device RAM
     * Examples: 934MB, 459MB, 1942MB, 3770MB
     */
    @ColumnInfo(name = "ram")
    val ram: String = "",

    /**
     * Device form factor.
     * Examples: Phone, TV, Tablet
     */
    @ColumnInfo(name = "type")
    val formFactor: String = "",

    /**
     * Examples: Mediatek MT6572A, Qualcomm MSM8909, Rockchip RK3326, Spreadtrum SC9832A
     */
    @ColumnInfo(name = "processor_name")
    val processorName: String = ""
)
