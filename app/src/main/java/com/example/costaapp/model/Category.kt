package com.example.costaapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Category(

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("pluralName")
    @Expose
    var pluralName: String? = null,

    @SerializedName("icon")
    @Expose
    var icon: Icon? = null
)
