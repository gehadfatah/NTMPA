package com.goda.npmoa.data_layer.model


import com.google.gson.annotations.SerializedName

data class Errors(
    @SerializedName("current_password")
    var currentPassword: List<String> = listOf()
)