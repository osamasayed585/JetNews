package com.droidos.data.remote

import com.droidos.common.utils.Constants.ALL
import com.droidos.common.utils.Constants.INITIAL_PAGE
import com.droidos.common.utils.Constants.PAGE_SIZE
import com.droidos.common.utils.Constants.PUBLISHED_AT
import com.droidos.model.beans.NetworkArticle
import com.droidos.network.di.errorHandler.BaseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesService {

    @GET("everything")
    suspend fun requestArticles(
        @Query("q") query: String = ALL,
        @Query("sortBy") sortBy: String = PUBLISHED_AT,
        @Query("language") language: String,
        @Query("pageSize") pageSize: Int = PAGE_SIZE,
        @Query("page") page: Int = INITIAL_PAGE,
    ): Response<BaseResponse<List<NetworkArticle>>>

    @GET("everything")
    suspend fun requestArticleDetail(
        @Query("q") query: String = ALL,
        @Query("sortBy") sortBy: String = PUBLISHED_AT,
        @Query("language") language: String,
        @Query("pageSize") pageSize: Int = PAGE_SIZE,
        @Query("page") page: Int = INITIAL_PAGE,
    ): Response<BaseResponse<List<NetworkArticle>>>
}