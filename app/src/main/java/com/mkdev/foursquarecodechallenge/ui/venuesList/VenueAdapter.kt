package com.mkdev.foursquarecodechallenge.ui.venuesList

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.mkdev.domain.model.Venue
import com.mkdev.foursquarecodechallenge.R
import com.mkdev.foursquarecodechallenge.base.BaseAdapter
import com.mkdev.foursquarecodechallenge.databinding.ItemVenueListBinding
import com.mkdev.foursquarecodechallenge.extension.computeLocationDistance
import javax.inject.Inject

class VenueAdapter @Inject constructor(
    private val glide: RequestManager
) : BaseAdapter<Venue>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Venue>() {
        override fun areItemsTheSame(oldItem: Venue, newItem: Venue): Boolean {
            Log.d("mytag", "${oldItem.id} , ${newItem.id}")
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Venue, newItem: Venue): Boolean {
            Log.d("mytag", "${oldItem.id} , ${newItem.id}")
            return oldItem.name == newItem.name
        }
    }

    override val differ: AsyncListDiffer<Venue> = AsyncListDiffer(this, diffCallback)

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemVenueListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return VenueViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    inner class VenueViewHolder(private val binding: ItemVenueListBinding) :
        RecyclerView.ViewHolder(binding.root), Binder<Venue> {
        override fun bind(item: Venue) {
            binding.apply {
                textViewVenueName.text = item.name
                textViewCategory.text = item.categoryType
                textViewDistance.text = itemView.context.getString(
                    R.string.less_than,
                    item.distance.computeLocationDistance()
                )
                itemView.context.getString(R.string.less_than)
                glide.load(item.categoryIcon).into(imageViewVenue)

                root.setOnClickListener {
                    onItemClickListener?.let { itemClick ->
                        itemClick(item)
                    }
                }
            }
        }
    }
}