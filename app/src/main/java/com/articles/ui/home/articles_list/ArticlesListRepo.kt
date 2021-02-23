package com.articles.ui.home.articles_list

import androidx.lifecycle.MutableLiveData
import com.articles.base.NetworkCallback
import com.articles.data.api.ApiManager
import com.articles.data.model.Event
import com.articles.data.model.FailureResponse
import com.articles.data.model.WrappedResponse
import com.articles.data.model.article.ArticleItem
import com.articles.data.model.article.GetArticlesListRequest

class ArticlesListRepo {

    internal fun hitGetArticlesListApi(gifListRequest: GetArticlesListRequest): MutableLiveData<Event<WrappedResponse<ArrayList<ArticleItem>>>> {

        val articlesListResponseLiveData =
            MutableLiveData<Event<WrappedResponse<ArrayList<ArticleItem>>>>()
        val wrappedResponse = WrappedResponse<ArrayList<ArticleItem>>()

        ApiManager.hitGetArticlesListApi(gifListRequest)
            .enqueue(object : NetworkCallback<ArrayList<ArticleItem>>() {
                override fun onSuccess(
                    t: ArrayList<ArticleItem>?
                ) {
                    wrappedResponse.data = t
                    articlesListResponseLiveData.value = Event(wrappedResponse)
                }

                override fun onFailure(failureResponse: FailureResponse) {
                    wrappedResponse.failureResponse = failureResponse
                    articlesListResponseLiveData.value = Event(wrappedResponse)
                }

                override fun onError(t: Throwable) {
                    wrappedResponse.failureResponse = FailureResponse.genericError()
                    articlesListResponseLiveData.value = Event(wrappedResponse)
                }
            })

        return articlesListResponseLiveData
    }

}