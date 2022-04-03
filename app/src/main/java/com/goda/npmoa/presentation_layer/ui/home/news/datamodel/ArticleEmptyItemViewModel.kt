package com.goda.npmoa.presentation_layer.ui.home.news.datamodel

import com.goda.npmoa.presentation_layer.ui.base.listeners.BaseEmptyItemListener

class ArticleEmptyItemViewModel(private val mListener: BaseEmptyItemListener) {
    fun onRetryClick() {
        mListener.onRetryClick()
    }

}