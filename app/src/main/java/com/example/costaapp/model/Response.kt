package com.example.costaapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Response(

    @SerializedName("headerFullLocation")
    @Expose
    var location: String? = null,

    @SerializedName("groups")
    @Expose
    var groups: List<Group>? = null
)
