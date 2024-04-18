package com.dqc.home.domain.usecase

import com.dqc.base.domain.result.Result
import com.dqc.home.domain.model.Article
import com.dqc.home.domain.repository.HomeRespository

internal class GetTopArtclesUseCase(
    private val homeRespository: HomeRespository,
) {
    suspend operator fun invoke(): Result<List<Article>> {
        val result = homeRespository.getArtcleTopInfo()

        return result
    }
}