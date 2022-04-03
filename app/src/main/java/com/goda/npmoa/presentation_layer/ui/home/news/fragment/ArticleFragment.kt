package com.goda.npmoa.presentation_layer.ui.home.news.fragment

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.goda.npmoa.BR
import com.goda.npmoa.R
import com.goda.npmoa.data_layer.common.ApplicationIntegration
import com.goda.npmoa.data_layer.common.ConnectionLiveData
import com.goda.npmoa.presentation_layer.ui.base.di.ViewModelProviderFactory
import com.goda.npmoa.databinding.FragmentNewsBinding
import com.goda.npmoa.presentation_layer.common.showSnakeBar
import com.goda.npmoa.presentation_layer.ui.base.ui.BaseFragment
import com.goda.npmoa.presentation_layer.ui.home.HomeActivity
import com.goda.npmoa.presentation_layer.ui.home.news.ArticleNavigator
import com.goda.npmoa.presentation_layer.ui.home.news.adapter.ArticleAdapter
import com.goda.npmoa.presentation_layer.ui.home.news.adapter.ArticleAdapter.ArticleAdapterListener
import com.goda.npmoa.presentation_layer.ui.home.news.datamodel.ArticleDataItem
import com.goda.npmoa.presentation_layer.ui.home.news.viewmodel.ArticleViewModel
import com.goda.npmoa.utils.NYPConstants
import javax.inject.Inject

class ArticleFragment : BaseFragment<FragmentNewsBinding, ArticleViewModel>(),
    ArticleNavigator, ArticleAdapterListener {
    @Inject
    lateinit var factory: ViewModelProviderFactory
    @Inject
    lateinit var articleAdapter: ArticleAdapter
    private var articleViewModel: ArticleViewModel? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_news

    override val viewModel: ArticleViewModel
        get() {
            articleViewModel = ViewModelProvider(this, factory).get(ArticleViewModel::class.java)
            return articleViewModel as ArticleViewModel
        }

    override fun onRetryClick() {
        articleViewModel?.fetchArticles(7)
    }

    override fun onItemClick(item: ArticleDataItem) {
        val bundle = Bundle()
        bundle.putParcelable(
            NYPConstants.ARTICLE,
            item
        )
        getNavController().navigate(R.id.action_articleFragment_to_articleDetailsFragment, bundle)
    }

    override fun handleError(message: String?) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun setData(data: List<ArticleDataItem>) {
        articleAdapter.addItems(data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        articleViewModel?.setNavigator(this)
        articleAdapter.setListener(this)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        if (activity != null) (activity as HomeActivity).setSupportActionBar(
            getViewDataBinding().toolbar
        )
        setHasOptionsMenu(true)
        setUpRecyclerView()
        setNetworkListner()
    }

    private fun setUpRecyclerView() {
        getViewDataBinding().resultsBeanRecyclerView.layoutManager = LinearLayoutManager(activity)
        getViewDataBinding().resultsBeanRecyclerView.itemAnimator = DefaultItemAnimator()
        getViewDataBinding().resultsBeanRecyclerView.adapter = articleAdapter
    }

    override fun onCreateOptionsMenu(
        menu: Menu,
        inflater: MenuInflater
    ) {
        inflater.inflate(R.menu.main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    private fun setNetworkListner() {
        /* Live data object and setting an observer on it to monitor connection status to update countries  */
        val connectionLiveData =
            ConnectionLiveData(ApplicationIntegration.getApplication())
        connectionLiveData.observe(viewLifecycleOwner, Observer { connection ->
            /* every time connection state changes, we'll be notified and can perform action accordingly */
            if (connection != null && isAdded) {
                if (connection.isConnected) {
                    // callFetchTags()
                    onRetryClick()
                } else {
                    getViewDataBinding().addressLookingUp.showSnakeBar(getString(R.string.no_internet_connection))
                    Log.d("d", "onChanged: ")

                }
            }
        })

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorites) {
            getNavController().navigate(R.id.action_articleFragment_to_favoritesFragment)
        }
        return super.onOptionsItemSelected(item)
    }
}