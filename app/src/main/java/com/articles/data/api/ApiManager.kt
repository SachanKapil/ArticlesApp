package com.articles.data.api

import com.articles.BuildConfig
import com.articles.data.model.BaseResponse
import com.articles.data.model.article.GetArticlesListRequest
import com.articles.data.model.article.ArticleItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by {Kapil Sachan} on 22/02/2021.
 */

object ApiManager {
    private val apiClient: ApiClient

    init {
        apiClient = httpClient
    }

    private val httpClient: ApiClient
        get() {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.API_BASE_URL)
                .client(getHttpClient().build())
                .build()

            return retrofit.create(ApiClient::class.java)
        }

    private fun getHttpClient(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .addInterceptor(getLoggingInterceptor())
            .readTimeout(30000, TimeUnit.MILLISECONDS)
            .writeTimeout(30000, TimeUnit.MILLISECONDS)
    }

    private fun getLoggingInterceptor(): HttpLoggingInterceptor {
        return if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor(CustomHttpLogger())
            httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        } else {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    fun hitGetArticlesListApi(getGifListRequest: GetArticlesListRequest): Call<BaseResponse<ArrayList<ArticleItem>>> {
        return apiClient.hitGetArticlesListApi(getGifListRequest.rssUrl)
    }
}