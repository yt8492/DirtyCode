package com.yt8492.dirtycode.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.yt8492.dirtycode.data.json.ArticleJson

class ArticleListAdapter : ListAdapter<ArticleJson, ArticleListViewHolder>(CALLBACK) {

    var onArticleClickListener: OnArticleClickListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleListViewHolder = ArticleListViewHolder.create(
        LayoutInflater.from(parent.context),
        parent,
        false
    )

    override fun onBindViewHolder(holder: ArticleListViewHolder, position: Int) {
        holder.bind(getItem(position), onArticleClickListener)
    }

    companion object {
        private val CALLBACK = object : DiffUtil.ItemCallback<ArticleJson>() {
            override fun areItemsTheSame(oldItem: ArticleJson, newItem: ArticleJson): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ArticleJson, newItem: ArticleJson): Boolean {
                return oldItem == newItem
            }
        }
    }
}