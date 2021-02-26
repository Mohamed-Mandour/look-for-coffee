package com.example.costaapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Venue (

    @PrimaryKey(autoGenerate = true)
    @SerializedName("venue_id")
    @Expose
    var venueId: Int? = null,

    @SerializedName("id")
    @Expose
    var id: String? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("verified")
    @Expose
    var verified: Boolean? = null,

    @SerializedName("categories")
    @Expose
    var categories: List<Category>? = null
)
