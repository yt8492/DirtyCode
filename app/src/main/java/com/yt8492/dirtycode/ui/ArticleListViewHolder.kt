package com.yt8492.dirtycode.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yt8492.dirtycode.data.json.ArticleJson
import com.yt8492.dirtycode.databinding.ItemArticleBinding

class ArticleListViewHolder(val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        article: ArticleJson?,
        onArticleClickListener: OnArticleClickListener?
    ) {
        binding.article = article
        binding.root.setOnClickListener {
            onArticleClickListener?.onClick(article)
        }
        binding.executePendingBindings()
    }

    companion object {
        fun create(
            inflater: LayoutInflater,
            container: ViewGroup,
            attachToRoot: Boolean
        ): ArticleListViewHolder = ArticleListViewHolder(
            ItemArticleBinding.inflate(
                inflater,
                container,
                attachToRoot
            )
        )
    }
}
