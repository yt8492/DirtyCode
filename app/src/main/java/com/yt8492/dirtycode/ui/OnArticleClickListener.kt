package com.yt8492.dirtycode.ui

import com.yt8492.dirtycode.data.model.Article

interface OnArticleClickListener {
    fun onClick(article: Article?)
}