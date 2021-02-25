package com.example.costaapp.model

import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
class Item {
    @SerializedName("reason")
    @Expose
    var reason: Any? = null

    @SerializedName("venue")
    @Expose
    var venue: Venue? = null
}
