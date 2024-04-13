package com.dqc.home.data.datasource.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class TagListApiModel(
    @SerialName("tag") val tag: List<TagApiModel>,
)