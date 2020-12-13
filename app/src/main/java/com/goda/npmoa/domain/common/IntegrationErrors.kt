package com.goda.npmoa.domain.common

import com.goda.npmoa.data_layer.model.ErrorServer

class UnAuthorizedException(message: String) : Exception(message)
class InternalServerErrorException : Exception()
class RequestErrorException(  message: String) : Exception( message)
class RequestErrorServerException(  errorServer: ErrorServer) : Exception( ) {
    var errorServer= ErrorServer()
    init {
       this.errorServer=errorServer
    }
}
