package com.goda.npmoa.presentation_layer.ui.home.favorites

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.goda.npmoa.BR
import com.goda.npmoa.R
import com.goda.npmoa.presentation_layer.ui.base.ViewModelProviderFactory
import com.goda.npmoa.data_layer.model.db.Article
import com.goda.npmoa.databinding.FragmentFavoritesBinding
import com.goda.npmoa.presentation_layer.ui.base.BaseFragment
import com.goda.npmoa.presentation_layer.ui.home.HomeActivity
import com.goda.npmoa.presentation_layer.ui.home.news.ArticleDataItem
import com.goda.npmoa.presentation_layer.ui.home.favorites.FavoritesAdapter.FavoritesAdapterListener
import com.goda.npmoa.utils.NYPConstants
import javax.inject.Inject

class FavoritesFragment :
    BaseFragment<FragmentFavoritesBinding, FavoritesViewModel>(),
    FavoritesNavigator,
    FavoritesAdapterListener {
    @Inject
    lateinit var factory: ViewModelProviderFactory
    @Inject
    lateinit var favoritesAdapter: FavoritesAdapter
    private var favoritesViewModel: FavoritesViewModel? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_favorites

    override val viewModel: FavoritesViewModel
        get() {
            favoritesViewModel = ViewModelProvider(this, factory).get(
                FavoritesViewModel::class.java
            )
            return favoritesViewModel as FavoritesViewModel
        }

    override fun onItemClick(item: Article) {
        val bundle = Bundle()
        bundle.putParcelable(
            NYPConstants.ARTICLE,
            ArticleDataItem(
                item.id
                , item.imageUrl
                , item.title

                , item.byline
                , item.section
                /*,  item.source*/
                , item.abstractX
                , item.publishedDate
                , item.url
                , item.coverImageUrl
            )
        )
        getNavController().navigate(
            R.id.action_favoritesFragment_to_articleDetailsFragment,
            bundle
        )
    }

    override fun handleError(message: String?) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun setData(data: List<Article>) {
        favoritesAdapter.addItems(data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoritesViewModel?.setNavigator(this)
        favoritesAdapter.setListener(this)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        if (activity != null) {
            (activity as HomeActivity).setSupportActionBar(getViewDataBinding().toolbar)
            getViewDataBinding().toolbar.title = getString(R.string.favorites)
            val actionBar = (activity as HomeActivity).supportActionBar
            actionBar?.setDisplayHomeAsUpEnabled(true)
            actionBar?.setDisplayShowHomeEnabled(true)
        }
        getViewDataBinding().toolbar.setNavigationOnClickListener {
            if (activity != null) {
                activity?.onBackPressed()
            }
        }
        setHasOptionsMenu(true)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        getViewDataBinding().favoritesRecyclerView.layoutManager = LinearLayoutManager(
            activity
        )
        getViewDataBinding().favoritesRecyclerView.itemAnimator = DefaultItemAnimator()
        getViewDataBinding().favoritesRecyclerView.adapter = favoritesAdapter
    }
}