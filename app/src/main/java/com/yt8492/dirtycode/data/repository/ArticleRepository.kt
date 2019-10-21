package com.yt8492.dirtycode.data.repository

import com.yt8492.dirtycode.data.model.Article

interface ArticleRepository {
    suspend fun findAll(
        query: String? = null,
        page: Int = 1,
        perPage: Int = 30
    ): List<Article>
}