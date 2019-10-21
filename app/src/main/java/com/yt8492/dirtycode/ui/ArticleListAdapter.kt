package com.yt8492.dirtycode.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.squareup.picasso.Picasso
import com.yt8492.dirtycode.data.json.ArticleJson
import com.yt8492.dirtycode.databinding.ItemArticleBinding

class ArticleListAdapter : ListAdapter<ArticleJson, ArticleListViewHolder>(CALLBACK) {

    var onArticleClickListener: OnArticleClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleListViewHolder {
        val binding = ItemArticleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ArticleListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleListViewHolder, position: Int) {
        val article = getItem(position)
        holder.binding.article = article
        holder.binding.root.setOnClickListener {
            onArticleClickListener?.onClick(article)
        }
        Picasso.get()
            .load(article.user.profileImageUrl)
            .into(holder.binding.avatar)
        holder.binding.executePendingBindings()
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