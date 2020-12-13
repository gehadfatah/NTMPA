package com.goda.npmoa.domain.common

import com.goda.npmoa.data_layer.model.ErrorServer
import com.goda.npmoa.data_layer.model.ErrorServerWithMessage

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class GenericError(val code: Int? = null, val error: ErrorServerWithMessage? = null): ResultWrapper<Nothing>()
    data class GenericErrorServer(val code: Int? = null, val error: ErrorServer? = null): ResultWrapper<Nothing>()
    object NetworkError: ResultWrapper<Nothing>()
}
