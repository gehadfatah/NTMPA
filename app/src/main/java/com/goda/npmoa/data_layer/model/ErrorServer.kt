package com.goda.npmoa.data_layer.model


import com.google.gson.annotations.SerializedName


data class ErrorServer (
    @SerializedName("@context")
    var context: String = "",
    @SerializedName("hydra:description")
    var hydraDescription: String = "",
    @SerializedName("hydra:title")
    var hydraTitle: String = "",
    @SerializedName("@type")
    var type: String = "",
    @SerializedName("violations")
    var violations: List<Violation> = listOf()
)