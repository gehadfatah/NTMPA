package com.goda.npmoa.presentation_layer.ui.home.news

import com.goda.npmoa.presentation_layer.ui.base.BaseEmptyItemListener

class ArticleEmptyItemViewModel(private val mListener: BaseEmptyItemListener) {
    fun onRetryClick() {
        mListener.onRetryClick()
    }

}