package com.dqc.home.domain.usecase

import com.dqc.base.domain.result.Result
import com.dqc.home.domain.model.Articles
import com.dqc.home.domain.repository.HomeRespository

internal class GetArticlesUseCase(
    private val homeRespository: HomeRespository,
) {
    suspend operator fun invoke(page: Int): Result<Articles> {
        val result = homeRespository.getArtcleInfo(page)

        return result
    }
}