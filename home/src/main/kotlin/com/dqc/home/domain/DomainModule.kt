package com.dqc.home.domain

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.dqc.home.domain.usecase.GetArticlesUseCase

internal val domainModule = module {
    singleOf(::GetArticlesUseCase)
}