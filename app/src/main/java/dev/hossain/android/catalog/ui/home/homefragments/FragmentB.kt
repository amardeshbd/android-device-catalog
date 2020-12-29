package dev.hossain.android.catalog.ui.home.homefragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.hossain.android.catalog.R
import dev.hossain.android.catalog.ui.home.HomeViewModel
import timber.log.Timber

/**
 * Demo fragment for tab content.
 *
 * TODO: Move the fragment to it's own feature package.
 */
@AndroidEntryPoint
class FragmentB : Fragment(R.layout.fragment_demo_content) {
    companion object {
        fun createInstance(): FragmentB {
            return FragmentB()
        }
    }

    val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("Got injected parent's viewmodel instance: %s.", viewModel)
    }
}
