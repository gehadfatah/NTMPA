package com.goda.npmoa.presentation_layer.ui.home.new_detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.goda.npmoa.BR
import com.goda.npmoa.R
import com.goda.npmoa.presentation_layer.ui.base.ViewModelProviderFactory
import com.goda.npmoa.databinding.FragmentArticleDetailsBinding
import com.goda.npmoa.presentation_layer.ui.base.BaseFragment
import com.goda.npmoa.presentation_layer.ui.home.HomeActivity
import com.goda.npmoa.presentation_layer.ui.home.news.ArticleDataItem
import com.goda.npmoa.utils.NYPConstants
import javax.inject.Inject

class ArticleDetailsFragment :
    BaseFragment<FragmentArticleDetailsBinding, ArticleDetailsViewModel>(),
    ArticleDetailsNavigator {
    @Inject
    lateinit var factory: ViewModelProviderFactory
    private var articleDetailsViewModel: ArticleDetailsViewModel? = null
    private var articleDataItem: ArticleDataItem? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_article_details

    override val viewModel: ArticleDetailsViewModel
        get() {
            articleDetailsViewModel =
                ViewModelProvider(this, factory).get(ArticleDetailsViewModel::class.java)
            return articleDetailsViewModel as ArticleDetailsViewModel
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        articleDetailsViewModel?.setNavigator(this)
        if (arguments != null) {
            articleDataItem = arguments?.getParcelable(NYPConstants.ARTICLE)
            if (articleDataItem != null) { // To check if article is favorite or not
                articleDataItem?.id?.let { articleDetailsViewModel?.findById(it) }
            }
        }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        setUpToolbar()
        setArticle()
    }

    private fun setArticle() {
        if (articleDataItem != null) {
            getViewDataBinding().article = articleDataItem
        }
    }

    private fun setUpToolbar() {
        if (activity != null) {
            (activity as HomeActivity).setSupportActionBar(getViewDataBinding().toolbar)
            val actionBar = (activity as HomeActivity).supportActionBar
            actionBar?.setDisplayHomeAsUpEnabled(true)
            actionBar?.setDisplayShowHomeEnabled(true)
            actionBar?.setDisplayShowTitleEnabled(false)
        }
        getViewDataBinding().toolbar.setNavigationOnClickListener {
            if (activity != null) {
                activity?.onBackPressed()
            }
        }
    }
}