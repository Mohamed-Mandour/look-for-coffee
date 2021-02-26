package com.example.costaapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseResponse(

    @SerializedName("response")
    @Expose
    var response: Response? = null
)