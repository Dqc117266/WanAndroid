package com.dqc.home.data.datasource.api.service

import com.dqc.base.data.api.BaseResponse
import com.dqc.base.data.retrofit.ApiResult
import com.dqc.home.data.datasource.api.model.ArticleApiModel
import com.dqc.home.data.datasource.api.model.BannerApiModel
import com.dqc.home.data.datasource.api.model.HomeArticleResultsApiModel
import retrofit2.http.GET
import retrofit2.http.Path

internal interface HomeRetrofitService {

    @GET("article/list/{page}/json")
    suspend fun getArticles(@Path("page") page: Int) : ApiResult<BaseResponse<HomeArticleResultsApiModel>>

    @GET("banner/json")
    suspend fun getBanner() : ApiResult<BaseResponse<List<BannerApiModel>>>

    @GET("article/top/json")
    suspend fun getTopArticles() : ApiResult<BaseResponse<List<ArticleApiModel>>>

}