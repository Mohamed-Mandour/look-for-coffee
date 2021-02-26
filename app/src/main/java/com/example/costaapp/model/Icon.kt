package com.example.costaapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Icon(

    @SerializedName("prefix")
    @Expose
    var prefix: String? = null,

    @SerializedName("suffix")
    @Expose
    var suffix: String? = null
)