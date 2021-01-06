package dev.hossain.android.catalog.ui.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import dev.hossain.android.catalog.R
import dev.hossain.android.catalog.databinding.FragmentManufacturerListBinding
import timber.log.Timber

/**
 * This activity shows how a recycler view is implemented using databinding and diffutils.
 */
@AndroidEntryPoint
class ManufacturersFragment : Fragment() {
    private val viewModel: ManufacturersViewModel by viewModels()
    private lateinit var binding: FragmentManufacturerListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        Timber.d("Got injected fragment's own viewmodel instance: %s.", viewModel)

        // Inflate the layout for this fragment using data binding and set the view model
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_manufacturer_list, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@ManufacturersFragment
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupIdeaList(binding.recyclerView)
    }

    private fun setupIdeaList(ideaList: RecyclerView) {

        val ideaListAdapter = ManufacturersAdapter { data ->
            Timber.d("Item Clicked: $data")
            viewModel.onItemClicked(data)

            // TODO test code
            findNavController().navigate(ManufacturersFragmentDirections.actionManufacturerListToDevicesFragment())
        }

        ideaList.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(context)
            adapter = ideaListAdapter
        }

        viewModel.data.observe(viewLifecycleOwner, Observer { result ->
            ideaListAdapter.submitList(result)
        })
    }
}
