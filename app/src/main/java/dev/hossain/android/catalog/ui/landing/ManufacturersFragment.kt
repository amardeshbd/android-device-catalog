package dev.hossain.android.catalog.ui.landing

import android.annotation.SuppressLint
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
import com.reddit.indicatorfastscroll.FastScrollItemIndicator
import dagger.hilt.android.AndroidEntryPoint
import dev.hossain.android.catalog.R
import dev.hossain.android.catalog.databinding.FragmentManufacturerListBinding
import dev.hossain.android.catalog.ui.landing.model.ItemModel
import timber.log.Timber

/**
 * This activity shows how a recycler view is implemented using databinding and diffutils.
 */
@AndroidEntryPoint
class ManufacturersFragment : Fragment() {
    private val viewModel: ManufacturersViewModel by viewModels()
    private lateinit var binding: FragmentManufacturerListBinding
    private var itemResult: List<ItemModel> = emptyList()

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
            setupFastScroller(result)
        })
    }

    @SuppressLint("DefaultLocale")
    private fun setupFastScroller(result: List<ItemModel>) {
        itemResult = result
        binding.fastscroller.setupWithRecyclerView(binding.recyclerView, { position ->
            val item = itemResult[position] // Get your model object
            FastScrollItemIndicator.Text(item.title.substring(0, 1).toUpperCase())
        })

        binding.fastscrollerThumb.setupWithFastScroller(binding.fastscroller)
    }
}
