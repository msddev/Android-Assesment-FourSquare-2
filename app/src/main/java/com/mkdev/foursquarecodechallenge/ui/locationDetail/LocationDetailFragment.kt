package com.mkdev.foursquarecodechallenge.ui.locationDetail

import androidx.fragment.app.viewModels
import com.mkdev.foursquarecodechallenge.base.BaseFragment
import com.mkdev.foursquarecodechallenge.databinding.FragmentLocationDetailBinding
import com.mkdev.presentation.viewmodels.LocationDetailViewModel

class LocationDetailFragment :
    BaseFragment<FragmentLocationDetailBinding, LocationDetailViewModel>() {

    override val viewModel: LocationDetailViewModel by viewModels()

    override fun getViewBinding(): FragmentLocationDetailBinding =
        FragmentLocationDetailBinding.inflate(layoutInflater)

}