package com.goda.npmoa.data_layer.model


import com.google.gson.annotations.SerializedName


data class ErrorServerWithMessage (
    @SerializedName("code")
    var code: String = "",
    @SerializedName("message")
    var message: String = ""

)