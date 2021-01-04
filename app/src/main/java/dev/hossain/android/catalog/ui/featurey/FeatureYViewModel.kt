package dev.hossain.android.catalog.ui.featurey

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.hossain.android.catalog.ui.featurey.model.ItemModel
import dev.hossain.android.catalogparser.Parser
import timber.log.Timber

class FeatureYViewModel @ViewModelInject constructor(val context: Context) : ViewModel() {
    private val _data = MutableLiveData<List<ItemModel>>()
    val data: LiveData<List<ItemModel>> = _data

    private val sampleData = mutableListOf<ItemModel>()

    init {
        generateSampleDataSet()
        _data.value = sampleData.toList()
    }

    private fun generateSampleDataSet() {
        // TEST CODE
        val catalog = context.assets.open("android-devices-catalog.csv")
        val parseDeviceCatalogData = Parser().parseDeviceCatalogData(catalog.bufferedReader().use { it.readText() })
        Timber.d("Got records: %s", parseDeviceCatalogData.size)

        sampleData.addAll(parseDeviceCatalogData.map { ItemModel(it.hashCode(), it.modelName) })
    }

    fun onItemClicked(item: ItemModel) {
        sampleData.remove(item)
        _data.value = sampleData.toList()
    }
}
