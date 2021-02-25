package com.example.costaapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Meta {

    @SerializedName("code")
    @Expose
    var code: Int? = null

    @SerializedName("requestId")
    @Expose
    var requestId: Any? = null
}
