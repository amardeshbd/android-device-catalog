package dev.hossain.android.catalog.ui.landing

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.hossain.android.catalog.data.AppDatabase
import dev.hossain.android.catalog.data.DeviceDao
import dev.hossain.android.catalog.data.model.AbiVersion
import dev.hossain.android.catalog.data.model.Device
import dev.hossain.android.catalog.data.model.OpenGLVersion
import dev.hossain.android.catalog.data.model.ScreenDensity
import dev.hossain.android.catalog.data.model.ScreenSize
import dev.hossain.android.catalog.data.model.SdkVersion
import dev.hossain.android.catalog.ui.landing.model.ItemModel
import dev.hossain.android.catalogparser.Parser
import dev.hossain.android.catalogparser.models.AndroidDevice
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class ManufacturersViewModel @ViewModelInject constructor(
    private val context: Context,
    private val appDatabase: AppDatabase
) : ViewModel() {
    private val _data = MutableLiveData<List<ItemModel>>()
    val data: LiveData<List<ItemModel>> = _data

    private val sampleData = mutableListOf<ItemModel>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            // TODO move this to separate importer class
            /*val androidCatalogDevices = parseCatalogData()
            insertAllCatalogDevices(androidCatalogDevices)*/

            testDataShowManufecturers()
        }
    }

    suspend fun parseCatalogData(): List<AndroidDevice> {
        val catalog = context.assets.open("android-devices-catalog.csv")
        val parseDeviceCatalogData = Parser().parseDeviceCatalogData(catalog.bufferedReader().use { it.readText() })

        Timber.d("Completed parsing catalog data. Got total %s records.", parseDeviceCatalogData.size)

        return parseDeviceCatalogData
    }

    suspend fun insertAllCatalogDevices(androidCatalogDevices: List<AndroidDevice>) {
        val deviceDao = appDatabase.deviceDao()

        androidCatalogDevices.forEach { androidDevice ->
            val deviceInsertId: Long = deviceDao.insert(androidDevice.toDevice())

            Timber.d("Inserted data with id: %s", deviceInsertId)

            insertDeviceAbis(deviceDao, deviceInsertId, androidDevice.abis)
            insertOpenGlVersions(deviceDao, deviceInsertId, androidDevice.openGlEsVersions)
            insertScreenSizes(deviceDao, deviceInsertId, androidDevice.screenSizes)
            insertScreenDensities(deviceDao, deviceInsertId, androidDevice.screenDensities)
            insertSdkVersions(deviceDao, deviceInsertId, androidDevice.sdkVersions)
        }
    }

    private suspend fun insertDeviceAbis(deviceDao: DeviceDao, deviceInsertId: Long, abis: List<String>) {
        Timber.d("insertDeviceAbis(): deviceInsertId = $deviceInsertId, abis = $abis")
        abis.forEach {
            deviceDao.insert(AbiVersion(abi = it, deviceId = deviceInsertId))
        }
    }

    private suspend fun insertOpenGlVersions(deviceDao: DeviceDao, deviceInsertId: Long, versions: List<String>) {
        Timber.d("insertOpenGlVersions(): deviceInsertId = $deviceInsertId, versions = $versions")
        versions.forEach {
            deviceDao.insert(OpenGLVersion(version = it, deviceId = deviceInsertId))
        }
    }

    private suspend fun insertScreenSizes(deviceDao: DeviceDao, deviceInsertId: Long, sizes: List<String>) {
        Timber.d("insertScreenSizes(): deviceInsertId = $deviceInsertId, sizes = $sizes")
        sizes.forEach {
            deviceDao.insert(ScreenSize(screenSize = it, deviceId = deviceInsertId))
        }
    }

    private suspend fun insertScreenDensities(deviceDao: DeviceDao, deviceInsertId: Long, densities: List<Int>) {
        Timber.d("insertScreenDensities(): deviceInsertId = $deviceInsertId, densities = $densities")
        densities.forEach {
            deviceDao.insert(ScreenDensity(density = it, deviceId = deviceInsertId))
        }
    }

    private suspend fun insertSdkVersions(deviceDao: DeviceDao, deviceInsertId: Long, apiLevels: List<Int>) {
        Timber.d("insertSdkVersions(): deviceInsertId = $deviceInsertId, apiLevels = $apiLevels")
        apiLevels.forEach {
            deviceDao.insert(SdkVersion(apiLevel = it, deviceId = deviceInsertId))
        }
    }

    suspend fun testDataShowManufecturers() {
        val deviceDao = appDatabase.deviceDao()
        val items = deviceDao.getManufacturers()

        sampleData.addAll(items.map {
            ItemModel(
                it.manufacturerName.hashCode(),
                "${it.manufacturerName} - ${it.totalDevicesMade}"
            )
        })

        _data.postValue(sampleData)
    }

    fun onItemClicked(item: ItemModel) {
        sampleData.remove(item)
        _data.value = sampleData.toList()
    }

    private fun AndroidDevice.toDevice() = Device(
        manufacturer = manufacturer,
        modelName = modelName,
        modelCode = modelCode,
        ram = ram,
        formFactor = formFactor,
        processorName = processorName
    )
}
