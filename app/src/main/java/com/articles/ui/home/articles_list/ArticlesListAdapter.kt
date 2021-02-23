package com.articles.ui.home.articles_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.articles.R
import com.articles.constants.AppConstants
import com.articles.data.model.article.ArticleItem
import com.articles.databinding.LayoutArticleBigItemBinding
import com.articles.databinding.LayoutArticleSmallItemBinding
import com.articles.utils.AppUtils
import com.articles.utils.ViewUtils


class ArticlesListAdapter(private val listener: ArticlesListAdapterListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var articlesList = ArrayList<ArticleItem>()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder {
        return when (position) {
            AppConstants.ViewTypeConstants.VIEW_TYPE_ARTICLE_BIG -> ViewHolderArticleBig(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.layout_article_big_item, parent, false
                )
            )
            AppConstants.ViewTypeConstants.VIEW_TYPE_ARTICLE_SMALL -> ViewHolderArticleSmall(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.layout_article_small_item, parent, false
                )
            )
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolderArticleBig -> {
                holder.bind(articlesList[position])
            }
            is ViewHolderArticleSmall -> {
                holder.bind(articlesList[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0)
            AppConstants.ViewTypeConstants.VIEW_TYPE_ARTICLE_BIG
        else {
            AppConstants.ViewTypeConstants.VIEW_TYPE_ARTICLE_SMALL
        }
    }

    fun clearAllData() {
        articlesList.clear()
        notifyDataSetChanged()
    }

    fun getList(): ArrayList<ArticleItem> {
        return articlesList
    }

    fun loadData(list: ArrayList<ArticleItem>) {
        articlesList.clear()
        articlesList.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolderArticleBig(private val binding: LayoutArticleBigItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(articleItem: ArticleItem) {
            ViewUtils.loadGif(
                binding.ivImage,
                binding.pbLoading,
                R.color.placeholder_grey,
                articleItem.thumbnail ?: ""
            )
            binding.tvTitle.text = articleItem.title
            articleItem.pubDate?.let {
                binding.tvDate.text = AppUtils.getDateWithUpdatedFormat(
                    it,
                    AppConstants.DateTimeFormatConstants.DATE_API_FORMAT,
                    AppConstants.DateTimeFormatConstants.DATE_FORMAT_MMM_DD_YYYY
                )
                binding.tvTime.text = AppUtils.getDateWithUpdatedFormat(
                    it,
                    AppConstants.DateTimeFormatConstants.DATE_API_FORMAT,
                    AppConstants.DateTimeFormatConstants.TIME_FORMAT_HH_MM_A
                )
            }
            binding.root.setOnClickListener {
                listener.onArticleClick(articleItem)
            }
        }
    }

    inner class ViewHolderArticleSmall(private val binding: LayoutArticleSmallItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(articleItem: ArticleItem) {
            ViewUtils.loadGif(
                binding.ivImage,
                binding.pbLoading,
                R.color.placeholder_grey,
                articleItem.enclosure?.link ?: ""
            )
            binding.tvTitle.text = articleItem.title
            articleItem.pubDate?.let {
                binding.tvDate.text = AppUtils.getDateWithUpdatedFormat(
                    it,
                    AppConstants.DateTimeFormatConstants.DATE_API_FORMAT,
                    AppConstants.DateTimeFormatConstants.DATE_FORMAT_MMM_DD_YYYY
                )
                binding.tvTime.text = AppUtils.getDateWithUpdatedFormat(
                    it,
                    AppConstants.DateTimeFormatConstants.DATE_API_FORMAT,
                    AppConstants.DateTimeFormatConstants.TIME_FORMAT_HH_MM_A
                )
            }
            binding.root.setOnClickListener {
                listener.onArticleClick(articleItem)
            }
        }
    }

    interface ArticlesListAdapterListener {
        fun onArticleClick(articleItem: ArticleItem)
    }
}