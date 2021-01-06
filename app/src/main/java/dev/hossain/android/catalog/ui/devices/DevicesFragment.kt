package dev.hossain.android.catalog.ui.devices

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.hossain.android.catalog.R
import dev.hossain.android.catalog.databinding.FragmentDevicesListBinding
import timber.log.Timber

@AndroidEntryPoint
class DevicesFragment : Fragment() {
    lateinit var binding: FragmentDevicesListBinding
    val viewModel: DevicesViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        Timber.d("Got injected fragment's own viewmodel instance: %s.", viewModel)

        // Inflate the layout for this fragment using data binding and set the view model
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_devices_list, container, false)
        binding.vm = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.titleText.setOnClickListener {
            findNavController().navigate(DevicesFragmentDirections.actionDevicesFragmentToDeviceDetailsFragment())
        }
    }
}
