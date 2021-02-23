package com.articles.ui.home.articles_list

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.articles.R
import com.articles.base.BaseFragment
import com.articles.data.DataManager
import com.articles.data.model.article.ArticleItem
import com.articles.databinding.FragmentArticlesListBinding
import com.articles.utils.AppUtils
import kotlinx.android.synthetic.main.layout_progress_bar.view.*

class ArticlesListFragment : BaseFragment<FragmentArticlesListBinding>(),
    SwipeRefreshLayout.OnRefreshListener, ArticlesListAdapter.ArticlesListAdapterListener,
    View.OnClickListener {
    private lateinit var binding: FragmentArticlesListBinding
    private lateinit var host: IArticlesListFragmentHost
    private lateinit var adapter: ArticlesListAdapter
    private lateinit var viewModel: ArticlesListViewModel

    companion object {
        fun getInstance(): ArticlesListFragment {
            return ArticlesListFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_articles_list
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is IArticlesListFragmentHost) {
            host = context
        } else {
            throw IllegalStateException("host must implement IArticlesListFragmentHost")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ArticlesListAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getViewDataBinding()
        setUpToolbar()
        initViewModel()
        initListener()
        initRecyclerView()
        initObservers()

        if (AppUtils.isNetworkAvailable(requireContext())) {
            binding.progressBarLayout.showLoading()
            hitGetArticlesListApi()
        } else {
            //support for offline mode
            DataManager.getArticlesListData()?.let { oldArticlesList ->
                adapter.loadData(oldArticlesList)
            } ?: let {
                binding.progressBarLayout.setErrorWithRetryButton(getString(R.string.message_no_internet_connection))
            }
        }
    }

    private fun setUpToolbar() {
        binding.appbar.tvTitle.text = getText(R.string.title_geeks_for_geeks)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[ArticlesListViewModel::class.java]
    }

    private fun initListener() {
        binding.refreshLayout.setOnRefreshListener(this)
        binding.refreshLayout.setColorSchemeResources(R.color.green)
        binding.progressBarLayout.btnTryAgain.setOnClickListener(this)
    }

    private fun initRecyclerView() {
        binding.rvArticles.adapter = adapter
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvArticles.layoutManager = layoutManager
    }


    private fun initObservers() {
        viewModel.getArticlesListResponseLiveData()
            .observe(viewLifecycleOwner, { wrappedResponseEvent ->
                if (wrappedResponseEvent != null && !wrappedResponseEvent.isAlreadyHandled) {
                    binding.progressBarLayout.hideLoading()
                    binding.refreshLayout.isRefreshing = false
                    val objectWrappedResponse = wrappedResponseEvent.getContent()
                    objectWrappedResponse?.failureResponse?.let {
                        onFailure(it)
                    } ?: let {
                        objectWrappedResponse?.data?.let { gifList ->
                            if (gifList.size != 0) {
                                adapter.loadData(gifList)
                                DataManager.saveArticlesListData(adapter.getList())
                            } else {
                                adapter.clearAllData()
                                DataManager.clearArticlesListData()
                                binding.progressBarLayout.setErrorWithOutRetryButton(
                                    getString(R.string.message_no_data_found)
                                )
                            }
                        }
                    }
                }
            })
    }

    override fun onRefresh() {
        if (AppUtils.isNetworkAvailable(requireContext())) {
            hitGetArticlesListApi()
        } else {
            binding.refreshLayout.isRefreshing = false
            showToastShort(getString(R.string.message_no_internet_connection))
        }
    }

    private fun hitGetArticlesListApi() {
        viewModel.hitGetArticlesListApi()
    }

    override fun onArticleClick(articleItem: ArticleItem) {
        host.openArticleDetailFragment(articleItem)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.progressBarLayout.btnTryAgain.id -> {
                if (AppUtils.isNetworkAvailable(requireContext())) {
                    binding.progressBarLayout.showLoading()
                    hitGetArticlesListApi()
                } else {
                    showToastShort(getString(R.string.message_no_internet_connection))
                    binding.progressBarLayout.setErrorWithRetryButton(getString(R.string.message_no_internet_connection))
                }
            }
        }
    }

    interface IArticlesListFragmentHost {
        fun openArticleDetailFragment(articleItem: ArticleItem)
    }
}