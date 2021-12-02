package com.mkdev.foursquarecodechallenge.ui.venueDetail

import androidx.fragment.app.viewModels
import com.mkdev.foursquarecodechallenge.base.BaseFragment
import com.mkdev.foursquarecodechallenge.databinding.FragmentVenueDetailBinding
import com.mkdev.presentation.viewmodels.VenueDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VenueDetailFragment :
    BaseFragment<FragmentVenueDetailBinding, VenueDetailViewModel>() {

    override val viewModel: VenueDetailViewModel by viewModels()

    override fun getViewBinding(): FragmentVenueDetailBinding =
        FragmentVenueDetailBinding.inflate(layoutInflater)

}