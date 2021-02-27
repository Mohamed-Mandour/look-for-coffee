package com.example.costaapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Reason (

    @SerializedName("items")
    @Expose
    var items: List<Summary>? = null
)