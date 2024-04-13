package com.dqc.home.data.datasource.api.model

import kotlinx.serialization.SerialName

internal data class HomeArticlesApiModel(
    @SerialName("datas") val articles: List<ArticleApiModel>
)
