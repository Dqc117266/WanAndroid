package com.dqc.base.domain.model

data class Response<T>(
    val data: T,
    val errorCode: Int,
    val errorMsg: String
)
