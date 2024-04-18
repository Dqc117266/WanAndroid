package com.dqc.home.data

import com.dqc.home.data.datasource.api.service.HomeRetrofitService
import com.dqc.home.data.repository.HomeRepositoryImpl
import com.dqc.home.domain.repository.HomeRespository
import org.koin.dsl.module
import retrofit2.Retrofit

internal val dataModule = module {
    single<HomeRespository> { HomeRepositoryImpl(get()) }

    single { get<Retrofit>().create(HomeRetrofitService::class.java) }
}