package com.dqc.home.data.datasource.api.model

import com.dqc.home.domain.model.Banner
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class BannerApiModel(
    @SerialName("desc") val desc: String,
    @SerialName("id") val id: Int,
    @SerialName("imagePath") val imagePath: String,
    @SerialName("isVisible") val isVisible: Int,
    @SerialName("order") val order: Int,
    @SerialName("title") val title: String,
    @SerialName("type") val type: Int,
    @SerialName("url") val url: String,
)

internal fun BannerApiModel.toDomainModel(): Banner {
    return Banner(
        desc = this.desc,
        id = this.id,
        imagePath = this.imagePath,
        isVisible = this.isVisible,
        order = this.order,
        title = this.title,
        type = this.type,
        url = this.url
    )
}
