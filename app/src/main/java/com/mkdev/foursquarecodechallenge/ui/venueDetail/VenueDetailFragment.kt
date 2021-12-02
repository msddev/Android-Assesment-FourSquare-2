package com.mkdev.foursquarecodechallenge.ui.venueDetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.mkdev.domain.model.VenueDetailUIModel
import com.mkdev.foursquarecodechallenge.R
import com.mkdev.foursquarecodechallenge.base.BaseFragment
import com.mkdev.foursquarecodechallenge.databinding.FragmentVenueDetailBinding
import com.mkdev.foursquarecodechallenge.extension.computeLocationDistance
import com.mkdev.foursquarecodechallenge.extension.makeGone
import com.mkdev.foursquarecodechallenge.extension.makeVisible
import com.mkdev.foursquarecodechallenge.extension.observe
import com.mkdev.presentation.viewmodels.VenueDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class VenueDetailFragment :
    BaseFragment<FragmentVenueDetailBinding, VenueDetailViewModel>() {

    override val viewModel: VenueDetailViewModel by viewModels()

    override fun getViewBinding(): FragmentVenueDetailBinding =
        FragmentVenueDetailBinding.inflate(layoutInflater)

    private val args: VenueDetailFragmentArgs by navArgs()

    @Inject
    lateinit var glide: RequestManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.venuesDetail, ::onViewStateChange)
        viewModel.getVenueDetail(args.venueId)
    }

    private fun onViewStateChange(result: VenueDetailUIModel) {
        if (result.isRedelivered) return
        when (result) {
            is VenueDetailUIModel.Loading -> {
                handleLoadingViews(true)
            }
            is VenueDetailUIModel.Success -> {
                handleLoadingViews(false)
                result.data.let { venue ->
                    binding.apply {
                        textViewVenueName.text = venue.name
                        textViewVenueAddress.text = venue.address
                        textViewVenuePhone.text = venue.phone
                        textViewDistance.text = getString(
                            R.string.less_than,
                            args.venueDistance.toInt().computeLocationDistance()
                        )
                        glide.load(venue.picture).into(imageViewVenue)
                    }
                }
            }
            is VenueDetailUIModel.Error -> {
                handleErrorMessage(result.error)
            }
        }
    }

    private fun handleLoadingViews(isLoading: Boolean) {
        if (isLoading) {
            binding.groupLoadingVenue.makeVisible()
            binding.fabVenueLocation.makeGone()
        } else {
            binding.groupLoadingVenue.makeGone()
            binding.fabVenueLocation.makeVisible()
        }
    }
}