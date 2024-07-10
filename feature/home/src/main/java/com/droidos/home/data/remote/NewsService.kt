package com.droidos.home.data.remote

import retrofit2.http.GET

interface NewsService {

    @GET("search")
    fun searchNews()
}