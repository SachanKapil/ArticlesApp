package com.articles.ui.home.articles_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.articles.data.model.Event
import com.articles.data.model.WrappedResponse
import com.articles.data.model.article.ArticleItem
import com.articles.data.model.article.GetArticlesListRequest

class ArticlesListViewModel : ViewModel() {

    private val repo = ArticlesListRepo()

    private val articlesListRequestLiveData = MutableLiveData<GetArticlesListRequest>()
    private val articlesListResponseLiveData =
        Transformations.switchMap(articlesListRequestLiveData) { request ->
            repo.hitGetArticlesListApi(
                request
            )
        }

    fun getArticlesListResponseLiveData(): LiveData<Event<WrappedResponse<ArrayList<ArticleItem>>>> {
        return articlesListResponseLiveData
    }

    fun hitGetArticlesListApi() {
        val getArticlesListRequest =
            GetArticlesListRequest("http://www.abc.net.au/news/feed/51120/rss.xml")
        articlesListRequestLiveData.value = getArticlesListRequest
    }
}