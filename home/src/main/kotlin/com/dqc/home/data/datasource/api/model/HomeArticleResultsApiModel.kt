package com.dqc.home.data.datasource.api.model

import com.dqc.home.domain.model.Articles
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class HomeArticleResultsApiModel(
    @SerialName("curPage") val curPage: Int,
    @SerialName("datas") val datas: List<ArticleApiModel>,
    @SerialName("offset") val offset: Int,
    @SerialName("over") val over: Boolean,
    @SerialName("pageCount") val pageCount: Int,
    @SerialName("size") val size: Int,
    @SerialName("total") val total: Int,
)

internal fun HomeArticleResultsApiModel.toDomainModel(): Articles {
    return Articles(
        curPage = this.curPage,
        datas = this.datas.map { it.toDomainModel() },
        offset = this.offset,
        over = this.over,
        pageCount = this.pageCount,
        size = this.size,
        total = this.total
    )
}