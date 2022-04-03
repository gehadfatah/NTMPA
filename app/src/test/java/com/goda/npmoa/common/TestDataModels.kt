package com.goda.npmoa.common

import com.goda.npmoa.data_layer.model.api.ArticlesResponse


val articlesResponse= ArticlesResponse().apply {
    articles= listOf<ArticlesResponse.Article>(
        ArticlesResponse.Article().apply {
            id=1
            title="title1"
            section="arts"
        },
        ArticlesResponse.Article().apply {
            id=2
            title="title2"
            section="Business"
        },ArticlesResponse.Article().apply {
            id=5
            title="title3"
            section="Science"
        }
    )
}

