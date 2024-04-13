package com.dqc.home.domain.repository

import com.dqc.base.domain.result.Result
import com.dqc.home.domain.model.Articles
import com.dqc.home.domain.model.Banner

internal interface HomeRespository {

    suspend fun getArtcleInfo(page: Int): Result<Articles>

    suspend fun getBanner(): Result<List<Banner>>

    suspend fun getArtcleTopInfo(): Result<Articles>

}