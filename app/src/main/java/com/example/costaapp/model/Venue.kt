package com.example.costaapp.model

import android.location.Location
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
class Venue {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("verified")
    @Expose
    var verified: Boolean? = null

    @SerializedName("location")
    @Expose
    var location: VenueLocation? = null

    @SerializedName("categories")
    @Expose
    var categories: List<VenueIcon>? = null

}
