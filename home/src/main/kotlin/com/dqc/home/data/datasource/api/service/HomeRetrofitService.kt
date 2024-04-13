package com.dqc.home.data.datasource.api.service

import com.dqc.base.data.retrofit.ApiResult
import com.dqc.home.data.datasource.api.response.HomeArticlesResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface HomeRetrofitService {

    @GET("./article/list/{page}/json")
    suspend fun getArticles(@Path("page") page: Int) : ApiResult<HomeArticlesResponse>

}