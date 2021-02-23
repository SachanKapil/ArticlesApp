package com.articles.ui.home

import android.os.Bundle
import com.articles.R
import com.articles.base.BaseActivity
import com.articles.data.model.article.ArticleItem
import com.articles.databinding.ActivityHomeBinding
import com.articles.ui.home.article_detail.ArticleDetailFragment
import com.articles.ui.home.articles_list.ArticlesListFragment

class HomeActivity : BaseActivity<ActivityHomeBinding>(),
    ArticlesListFragment.IArticlesListFragmentHost {

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openGifListingFragment()
    }

    private fun openGifListingFragment() {
        replaceFragment(
            R.id.fl_main, ArticlesListFragment.getInstance(),
            ArticlesListFragment::class.java.simpleName
        )
    }

    override fun openArticleDetailFragment(articleItem: ArticleItem) {
        addFragmentWithBackStackWithAnimation(
            R.id.fl_main, ArticleDetailFragment.getInstance(articleItem),
            ArticleDetailFragment::class.java.simpleName
        )
    }
}