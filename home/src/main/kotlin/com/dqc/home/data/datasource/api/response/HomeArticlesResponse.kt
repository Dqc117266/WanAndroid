package com.dqc.home.data.datasource.api.response

import com.dqc.home.data.datasource.api.model.HomeArticleResultsApiModel
import kotlinx.serialization.SerialName

internal data class HomeArticlesResponse(
    @SerialName("data") val results: HomeArticleResultsApiModel,
    @SerialName("errorCode") val errorCode: Int,
    @SerialName("errorMsg") val errorMsg: String
)