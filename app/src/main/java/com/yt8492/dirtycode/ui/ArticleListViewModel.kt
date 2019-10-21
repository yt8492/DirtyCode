package com.yt8492.dirtycode.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yt8492.dirtycode.data.model.Article
import com.yt8492.dirtycode.data.repository.ArticleRepository
import com.yt8492.dirtycode.data.repository.ArticleRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArticleListViewModel : ViewModel() {

    private val articleRepository: ArticleRepository = ArticleRepositoryImpl()

    val articleList = MutableLiveData<List<Article>>()

    fun search(query: String?): Job? = viewModelScope.launch {
        articleList.value = withContext(Dispatchers.IO) {
            articleRepository.findAll(query)
        }
    }
}