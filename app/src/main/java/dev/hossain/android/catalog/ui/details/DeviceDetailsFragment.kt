package dev.hossain.android.catalog.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.hossain.android.catalog.R
import dev.hossain.android.catalog.databinding.FragmentDeviceDetailsBinding
import timber.log.Timber

@AndroidEntryPoint
class DeviceDetailsFragment : Fragment() {

    lateinit var binding: FragmentDeviceDetailsBinding
    val viewModel: DeviceDetailsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        Timber.d("Got injected fragment's own viewmodel instance: %s.", viewModel)

        // Inflate the layout for this fragment using data binding and set the view model
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_device_details, container, false)
        binding.vm = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}
