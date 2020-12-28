package dev.hossain.android.catalog.ui.featurez

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.hossain.android.catalog.R
import dev.hossain.android.catalog.databinding.DialogFeatureZBinding

@AndroidEntryPoint
class FeatureZDialogFragment : DialogFragment() {
    lateinit var binding: DialogFeatureZBinding
    val viewModel: FeatureZViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_feature_z, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onPause() {
        super.onPause()
        dismiss()
    }
}
