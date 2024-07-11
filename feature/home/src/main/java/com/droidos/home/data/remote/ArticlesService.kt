package com.droidos.home.data.remote

import com.droidos.common.utils.Constants.INITIAL_PAGE
import com.droidos.common.utils.Constants.PAGE_SIZE
import com.droidos.common.utils.Constants.PUBLISHED_AT
import com.droidos.common.utils.Constants.QUERY
import com.droidos.home.data.model.response.ArticlesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesService {

    @GET("everything")
    fun requestArticles(
        @Query("q") query: String = QUERY,
        @Query("sortBy") sortBy: String = PUBLISHED_AT,
        @Query("language") language: String,
        @Query("pageSize") pageSize: Int = PAGE_SIZE,
        @Query("page") page: Int = INITIAL_PAGE,
    ): ArticlesResponse
}