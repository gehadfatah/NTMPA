package com.goda.npmoa.presentation_layer.ui.base

interface BaseNavigator<T> {
    fun handleError(message: String?)
    fun setData(data: T)
}