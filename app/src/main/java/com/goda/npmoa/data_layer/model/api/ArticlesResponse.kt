package com.goda.npmoa.data_layer.model.api

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


class ArticlesResponse {
    @SerializedName("results")
    var articles: List<Article>? = null

    class Article {
        var section: String?=null
        val source: String?=null
        var id: Long = 0
        var url: String? = null
        var byline: String? = null
        var type: String? = null
        var title: String? = null
        @SerializedName("abstract")
        var abstractX: String? = null
        @SerializedName("published_date")
        var publishedDate: String? = null
        var media: List<MediaBean>? = null

        class MediaBean {
            @SerializedName("media-metadata")
            var mediaMetaData: List<MediaMetaDataBean>? = null

            class MediaMetaDataBean {
                var url: String? = null
            }
        }
    }
}