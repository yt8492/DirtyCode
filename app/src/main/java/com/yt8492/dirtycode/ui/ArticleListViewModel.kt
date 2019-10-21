package com.yt8492.dirtycode.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.yt8492.dirtycode.BuildConfig
import com.yt8492.dirtycode.data.QiitaApi
import com.yt8492.dirtycode.data.json.ArticleJson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.URL
import java.util.*

class ArticleListViewModel : ViewModel() {
    private val qiitaApi = Retrofit.Builder()
        .baseUrl(URL(BuildConfig.QIITA_URL))
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .add(Date::class.java, Rfc3339DateJsonAdapter().nonNull())
                    .build()
            )
        )
        .build()
        .create(QiitaApi::class.java)

    val articleList = MutableLiveData<List<ArticleJson>>()

    fun search(query: String?): Job? = viewModelScope.launch {
        articleList.value = withContext(Dispatchers.IO) {
            qiitaApi.getArticles(query)
        }
    }
}