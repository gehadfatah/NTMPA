package com.goda.npmoa.data_layer.model


import com.google.gson.annotations.SerializedName

data class Violation(
    @SerializedName("message")
    var message: String = "",
    @SerializedName("propertyPath")
    var propertyPath: String = ""
)