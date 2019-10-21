package com.yt8492.dirtycode.data

import com.yt8492.dirtycode.data.json.ArticleJson
import retrofit2.http.GET
import retrofit2.http.Query

interface QiitaApi {
    @GET("api/v2/items")
    suspend fun getArticles(
        @Query("query") query: String?,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 30
    ): List<ArticleJson>
}