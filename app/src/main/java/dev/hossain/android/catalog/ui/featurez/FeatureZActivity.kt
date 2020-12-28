package dev.hossain.android.catalog.ui.featurez

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import dev.hossain.android.catalog.R
import dev.hossain.android.catalog.databinding.ActivityFeatureZBinding
import timber.log.Timber

/**
 * This activity shows how a fragment can be injected.
 *
 * @see FeatureZViewModel
 */
@AndroidEntryPoint
class FeatureZActivity : AppCompatActivity() {
    private val viewModel: FeatureZViewModel by viewModels()
    private lateinit var binding: ActivityFeatureZBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_feature_z)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        observeNavigationEvents(viewModel)
    }

    /**
     * TODO: This is an example of how LiveData can be used to navigate. Update accordingly.
     */
    private fun observeNavigationEvents(viewModel: FeatureZViewModel) {
        viewModel.showDialogEvent.observe(this, Observer {
            Timber.i("Showing dialog")
            val dialog = FeatureZDialogFragment()
            dialog.show(supportFragmentManager, "dialog")
        })
    }
}
