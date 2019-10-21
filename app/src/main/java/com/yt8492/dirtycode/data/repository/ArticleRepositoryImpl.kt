package com.yt8492.dirtycode.data.repository

import com.yt8492.dirtycode.data.api.QiitaApiFactory
import com.yt8492.dirtycode.data.converter.toModel
import com.yt8492.dirtycode.data.json.ArticleJson
import com.yt8492.dirtycode.data.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ArticleRepositoryImpl : ArticleRepository {

    private val qiitaApi = QiitaApiFactory().create()

    override suspend fun findAll(
        query: String?,
        page: Int,
        perPage: Int
    ): List<Article> = withContext(Dispatchers.IO) {
        qiitaApi.getArticles(
            query,
            page,
            perPage
        ).map(ArticleJson::toModel)
    }
}