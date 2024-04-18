package com.dqc.home.data.datasource.api.model

import com.dqc.home.domain.model.Tag
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class TagListApiModel(
    @SerialName("tag") val tag: List<TagApiModel>,
)

internal fun TagListApiModel.toDomainModel(): List<Tag> {
    return tag.map { it.toDomainModel() }
}