package com.dqc.home.data.repository

import com.dqc.base.data.retrofit.ApiResult
import com.dqc.base.domain.result.Result
import com.dqc.home.data.datasource.api.model.toDomainModel
import com.dqc.home.data.datasource.api.service.HomeRetrofitService
import com.dqc.home.domain.model.Articles
import com.dqc.home.domain.model.Banner
import com.dqc.home.domain.repository.HomeRespository
import timber.log.Timber

internal class HomeRepositoryImpl(
    private val homeRetrofitService: HomeRetrofitService,
) : HomeRespository {
    override suspend fun getArtcleInfo(page: Int): Result<Articles> =
        when (val apiResult = homeRetrofitService.getArticles(page)) {
            is ApiResult.Success -> {
                val artcles = apiResult.data.results
                Timber.d("HomeRepositoryImpl: ${artcles.toDomainModel()}")
                Result.Success(artcles.toDomainModel())
            }
            is ApiResult.Error -> {
                Timber.d("HomeRepositoryImpl: Error")
                Result.Failure()
            }
            is ApiResult.Exception -> {
                Timber.e("HomeRepositoryImpl: ${apiResult.throwable}")
                Result.Failure()
            }
        }

    override suspend fun getBanner(): Result<List<Banner>> =
        when (val apiResult = homeRetrofitService.getArticles(0)) {
            is ApiResult.Success -> {
//                val artcles = apiResult.data.results
//                Result.Success(artcles.toDomainModel())
                Result.Failure()
            }
            is ApiResult.Error -> {
                Result.Failure()
            }
            is ApiResult.Exception -> {
                Timber.e(apiResult.throwable)
                Result.Failure()
            }
        }

    override suspend fun getArtcleTopInfo(): Result<Articles> =
        when (val apiResult = homeRetrofitService.getArticles(0)) {
            is ApiResult.Success -> {
//                val artcles = apiResult.data.results
//                Result.Success(artcles.toDomainModel())
                Result.Failure()
            }
            is ApiResult.Error -> {
                Result.Failure()
            }
            is ApiResult.Exception -> {
                Timber.e(apiResult.throwable)
                Result.Failure()
            }
        }

//    override suspend fun getBanner(): Result<List<Banner>> {
//
//    }
//
//    override suspend fun getArtcleTopInfo(): Result<Articles> {
//    }
}