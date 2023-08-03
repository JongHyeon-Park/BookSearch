package com.bradpark.network.retrofit

import com.bradpark.network.model.BookSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitNetworkApi {

    @GET(value = "/v3/search/book")
    suspend fun getBookSearchResponse(
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int?,
        @Query("size") size: Int
    ): BookSearchResponse
}