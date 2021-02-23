package com.articles.ui.home.article_detail

import android.os.Bundle
import android.view.View
import com.articles.R
import com.articles.base.BaseFragment
import com.articles.constants.AppConstants
import com.articles.data.model.article.ArticleItem
import com.articles.databinding.FragmentArticleDetailsBinding
import com.articles.utils.ViewUtils

class ArticleDetailFragment : BaseFragment<FragmentArticleDetailsBinding>(), View.OnClickListener {
    private lateinit var binding: FragmentArticleDetailsBinding
    private lateinit var articleItem: ArticleItem


    companion object {
        fun getInstance(articleItem: ArticleItem): ArticleDetailFragment {
            val articleDetailFragment = ArticleDetailFragment()
            val bundle = Bundle().also {
                it.putParcelable(AppConstants.BundleConstants.KEY_ARTICLE_ITEM, articleItem)
            }
            articleDetailFragment.arguments = bundle
            return articleDetailFragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_article_details
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBundleData()
        initVariables()
        setUpToolbar()
        initListener()
        setUpUi()
    }

    private fun initVariables() {
        binding = getViewDataBinding()
    }

    private fun setUpToolbar() {
        binding.toolbar.tvTitle.text = getText(R.string.title_article_detail)
    }

    private fun initListener() {
        binding.toolbar.ivBack.setOnClickListener(this)
    }

    private fun getBundleData() {
        arguments?.let {
            articleItem = it.getParcelable(AppConstants.BundleConstants.KEY_ARTICLE_ITEM)!!
        }
    }

    private fun setUpUi() {
        ViewUtils.loadGif(
            binding.ivImage,
            binding.pbLoading,
            R.color.placeholder_grey,
            articleItem.thumbnail ?: ""
        )
        if (articleItem.author.isNullOrBlank()) {
            binding.tvAuthor.text = getString(R.string.message_not_available)
        } else {
            binding.tvAuthor.text = articleItem.author
        }
        if (articleItem.title.isNullOrBlank()) {
            binding.tvTitle.text = getString(R.string.message_not_available)
        } else {
            binding.tvTitle.text = articleItem.title
        }
        if (articleItem.description.isNullOrBlank()) {
            binding.tvDescription.text = getString(R.string.message_not_available)
        } else {
            binding.tvDescription.text = articleItem.description
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.toolbar.ivBack.id -> {
                requireActivity().onBackPressed()
            }
        }
    }
}