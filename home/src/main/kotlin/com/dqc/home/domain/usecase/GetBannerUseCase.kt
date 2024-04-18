package com.dqc.home.domain.usecase

import com.dqc.base.domain.result.Result
import com.dqc.home.domain.model.Banner
import com.dqc.home.domain.repository.HomeRespository

internal class GetBannerUseCase(
    private val homeRespository: HomeRespository
) {
    suspend operator fun invoke(): Result<List<Banner>> {
        val result = homeRespository.getBanner()

        return result
    }
}