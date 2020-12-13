package com.goda.npmoa.data_layer.remote.network

object ApiEndPoint {
    const val ENDPOINT_NEWS = "/svc/mostpopular/v2/viewed/{period}.json"
    const val ENDPOINT_ARTICLES = "/svc/mostpopular/v2/mostviewed/all-sections/{period}.json"
}