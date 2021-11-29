package com.mkdev.foursquarecodechallenge.ui.locationList

import androidx.fragment.app.viewModels
import com.mkdev.foursquarecodechallenge.base.BaseFragment
import com.mkdev.foursquarecodechallenge.databinding.FragmentLocationListBinding
import com.mkdev.presentation.viewmodels.LocationListViewModel
import javax.inject.Inject

class LocationListFragment : BaseFragment<FragmentLocationListBinding, LocationListViewModel>() {

    @Inject
    lateinit var locationListAdapter: LocationListAdapter

    override val viewModel: LocationListViewModel by viewModels()

    override fun getViewBinding(): FragmentLocationListBinding =
        FragmentLocationListBinding.inflate(layoutInflater)

}