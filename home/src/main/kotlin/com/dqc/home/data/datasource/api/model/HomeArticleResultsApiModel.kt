package com.dqc.home.data.datasource.api.model

import kotlinx.serialization.SerialName

internal data class HomeArticleResultsApiModel(
    @SerialName("curPage") val curPage: Int,
    @SerialName("datas") val datas: List<ArticleApiModel>,
    @SerialName("offset") val offset: Int,
    @SerialName("over") val over: Boolean,
    @SerialName("pageCount") val pageCount: Int,
    @SerialName("size") val size: Int,
    @SerialName("total") val total: Int,
)