package dev.hossain.android.catalog.ui.featurex

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import dev.hossain.android.catalog.R
import dev.hossain.android.catalog.databinding.ActivityFeatureXBinding
import dev.hossain.android.catalog.ui.common.Result
import dev.hossain.android.catalog.ui.extensions.onChanged
import dev.hossain.android.catalogparser.Parser
import timber.log.Timber

/**
 * This activity shows how web service API can be used via ViewModel.
 *
 * @see FeatureXViewModel
 */
@AndroidEntryPoint
class FeatureXActivity : AppCompatActivity() {

    private val viewModel: FeatureXViewModel by viewModels()
    private lateinit var binding: ActivityFeatureXBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_feature_x)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.message.onChanged { result ->
            when (result) {
                is Result.Success -> {
                    binding.messageText.text = result.data
                }
                is Result.Error -> {
                    binding.messageText.text = result.exception.message
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()

        // TEST CODE
        val catalog = assets.open("android-devices-catalog.csv")
        val parseDeviceCatalogData = Parser().parseDeviceCatalogData(catalog.bufferedReader().use { it.readText() })
        Timber.d("Got records: %s", parseDeviceCatalogData.size)

        viewModel.testDb(this.applicationContext)
    }
}
