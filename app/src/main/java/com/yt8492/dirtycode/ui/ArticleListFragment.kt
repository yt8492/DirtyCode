package com.yt8492.dirtycode.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.yt8492.dirtycode.R
import com.yt8492.dirtycode.data.model.Article
import com.yt8492.dirtycode.databinding.FragmentArticleListBinding

class ArticleListFragment : Fragment() {

    private val onArticleClickListener = object : OnArticleClickListener {
        override fun onClick(article: Article?) {
            article?.let {
                val tabsIntent = CustomTabsIntent.Builder()
                    .setShowTitle(true)
                    .build()
                tabsIntent.launchUrl(requireContext(), article.url.toUri())
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val query = arguments?.getString(KEY_QUERY)
        val binding = FragmentArticleListBinding.inflate(
            inflater,
            container,
            false)
        val articleListAdapter = ArticleListAdapter().apply {
            onArticleClickListener = this@ArticleListFragment.onArticleClickListener
        }
        binding.lifecycleOwner = viewLifecycleOwner
        binding.articleListRecyclerView.apply {
            adapter = articleListAdapter
            val layoutManager = LinearLayoutManager(inflater.context)
            val dividerItemDecoration =
                DividerItemDecoration(inflater.context, layoutManager.orientation)
            addItemDecoration(dividerItemDecoration)
        }
        val viewModel = ViewModelProvider(this).get(ArticleListViewModel::class.java)
        viewModel.articleList.observe(viewLifecycleOwner, Observer {
            articleListAdapter.submitList(it)
        })
        viewModel.search(query)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_article_list, menu)
        val menuItem = menu.findItem(R.id.toolbarSearch)
        val searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun search(query: String?) {
        val intent = ArticleListActivity.createIntent(
            requireContext(),
            query
        )
        startActivity(intent)
    }

    companion object {
        private const val KEY_QUERY = "QUERY"

        @JvmStatic
        fun newInstance(query: String?) = ArticleListFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_QUERY, query)
            }
        }
    }
}