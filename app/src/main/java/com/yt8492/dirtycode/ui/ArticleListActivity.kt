package com.yt8492.dirtycode.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.yt8492.dirtycode.R

class ArticleListActivity : AppCompatActivity() {

    private val query: String? by lazy {
        intent.getStringExtra(KEY_QUERY)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_list)
        val articlesFragment =
            ArticleListFragment.newInstance(query)
        supportFragmentManager.commit {
            add(R.id.fragmentContainer, articlesFragment)
        }
    }

    companion object {
        const val KEY_QUERY = "QUERY"

        fun createIntent(context: Context, query: String?): Intent =
            Intent(context, ArticleListActivity::class.java).apply {
                putExtra(KEY_QUERY, query)
            }
    }
}
