package com.example.costaapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MetaResponse {
    @SerializedName("meta")
    @Expose
    var meta: Meta? = null

    @SerializedName("response")
    @Expose
    var response: Response? = null
}