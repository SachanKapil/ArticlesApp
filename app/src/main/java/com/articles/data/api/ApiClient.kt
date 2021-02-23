package com.articles.data.api

import com.articles.data.model.BaseResponse
import com.articles.data.model.article.ArticleItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by {Kapil Sachan} on 22/02/2021.
 */

interface ApiClient {

    @GET("api.json")
    fun hitGetArticlesListApi(@Query("rss_url") rss_url: String): Call<BaseResponse<ArrayList<ArticleItem>>>

}