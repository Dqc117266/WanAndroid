package com.dqc.base.data.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    @SerialName("data") val results: T,
    @SerialName("errorCode") val errorCode: Int,
    @SerialName("errorMsg") val errorMsg: String
)
