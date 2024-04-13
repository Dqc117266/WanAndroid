package com.dqc.home.data.datasource.api.model

import com.dqc.home.data.datasource.database.model.TagEntityModel
import com.dqc.home.domain.model.Tag
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class TagApiModel(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String,
)

internal fun TagApiModel.toDomainModel() = Tag(
    name = this.name,
    url = this.url
)

internal fun TagApiModel.toEntityModel() = TagEntityModel(
    name = this.name,
    url = this.url
)