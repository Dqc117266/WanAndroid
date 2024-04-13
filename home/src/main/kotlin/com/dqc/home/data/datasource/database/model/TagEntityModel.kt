package com.dqc.home.data.datasource.database.model

import com.dqc.home.domain.model.Tag
import kotlinx.serialization.Serializable

@Serializable
internal data class TagEntityModel(
    val name: String,
    val url: String
)

internal fun TagEntityModel.doDomainModel() = Tag(
    name = this.name,
    url = this.url
)
