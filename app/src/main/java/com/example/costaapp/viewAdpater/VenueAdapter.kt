package com.example.costaapp.viewAdpater

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.costaapp.R
import com.example.costaapp.model.Reason
import com.example.costaapp.model.Venue
import com.example.costaapp.utils.inflate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.venue_item_layout.view.*

private const val VENUE_ICON_SIZE = "64"
private const val VERIFIED = "This place is verified"
private const val NOT_VERIFIED = "This place is not verified"
class VenueAdapter(private val venuesList: MutableList<Venue>, private val reasonList: MutableList<Reason>) : RecyclerView.Adapter<VenueAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.venue_item_layout)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(venuesList[position], reasonList[position])
    }

    override fun getItemCount() = venuesList.size

    fun setVenue(venue: MutableList<Venue>, reason: MutableList<Reason>) {
        this.venuesList.clear()
        this.venuesList.addAll(venue)
        this.reasonList.clear()
        this.reasonList.addAll(reason)
        notifyDataSetChanged()
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(venue: Venue, reason: Reason) = with(view) {
            venue_name.text = venue.name
            setVenueSummary(reason)
            setVerifiedVenue(venue)
            setVenueIcon(venue)
        }

        private fun View.setVenueSummary(reason: Reason) {
            val summaries = reason.items
            if (summaries != null) {
                for (summary in summaries) {
                    venue_summary.text = summary.summary
                }
            }
        }

        private fun View.setVenueIcon(venue: Venue) {
            val categories = venue.categories
            if (categories != null) {
                for (category in categories) {
                    val iconUrl = category.icon?.prefix + VENUE_ICON_SIZE + category.icon?.suffix
                    venue_type.text = category.name
                    Picasso.get().load(iconUrl).into(venue_image_logo)
                }
            }
        }

        private fun View.setVerifiedVenue(venue: Venue) {
            val verified = venue.verified.toString()
            if (verified.isNotEmpty() && verified == "true") {
                venue_verified.setTextColor(resources.getColor(R.color.green))
                venue_verified.text = VERIFIED
            } else {
                venue_verified.setTextColor(resources.getColor(R.color.amber))
                venue_verified.text = NOT_VERIFIED
            }
        }

    }
}