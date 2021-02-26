package com.example.costaapp.model

import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Item(

    @SerializedName("venue")
    @Expose
    var venue: Venue? = null
)
