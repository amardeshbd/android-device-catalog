package dev.hossain.android.catalog.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Collections.emptyList

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
    val processorName: String = "",

    /**
     * Examples: 1024x600, 1366x768, 480x854, 800x1280
     */
    @ColumnInfo(name = "screen_sizes")
    val screenSizes: List<String> = emptyList(),

    /**
     * Examples: 160, 213, 240, 300, 320
     */
    @ColumnInfo(name = "screen_densities")
    val screenDensities: List<Int> = emptyList(),

    /**
     * Examples: arm64-v8a, armeabi, armeabi-v7a
     */
    @ColumnInfo(name = "abis")
    val abis: List<String> = emptyList(),

    /**
     * Examples: 23, 24, 27
     */
    @ColumnInfo(name = "sdk_versions")
    val sdkVersions: List<Int> = emptyList(),

    /**
     * Examples: 2.0, 3.0, 3.1, 3.2
     */
    @ColumnInfo(name = "opengl_versions")
    val openGlEsVersions: List<String> = emptyList()
)
