package com.example.costaapp.viewAdpater

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.costaapp.R
import com.example.costaapp.model.Venue
import com.example.costaapp.utils.inflate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.venue_item_layout.view.*

private const val VENUE_ICON_SIZE = "64"

class VenueAdapter(private val venuesList: MutableList<Venue>) : RecyclerView.Adapter<VenueAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.venue_item_layout)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(venuesList[position])
    }

    override fun getItemCount() = venuesList.size

    fun setVenue(venue: MutableList<Venue>) {
        this.venuesList.clear()
        this.venuesList.addAll(venue)
        notifyDataSetChanged()
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(venue: Venue) = with(view) {
            venue_name.text = venue.name
            venue_verified.text = venue.verified.toString()
            val categories = venue.categories
            if (categories != null) {
                for (category in categories) {
                    val iconUrl = category.icon?.prefix + VENUE_ICON_SIZE + category.icon?.suffix

                    Picasso.get().load(iconUrl).into(venue_image_logo)
                }
            }
        }

    }
}