package com.yt8492.dirtycode.ui

import com.yt8492.dirtycode.data.json.ArticleJson

interface OnArticleClickListener {
    fun onClick(article: ArticleJson?)
}