package com.dqc.home.domain

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.dqc.home.domain.usecase.GetArticlesUseCase
import com.dqc.home.domain.usecase.GetBannerUseCase
import com.dqc.home.domain.usecase.GetTopArtclesUseCase

internal val domainModule = module {
    singleOf(::GetArticlesUseCase)
    singleOf(::GetTopArtclesUseCase)
    singleOf(::GetBannerUseCase)
}