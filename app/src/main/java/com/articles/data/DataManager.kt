package com.articles.data

import com.airhireme.data.preferences.PreferenceManager
import com.articles.constants.AppConstants
import com.articles.data.model.article.ArticleItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 * Created by {Kapil Sachan} on 22/02/2021.
 */

object DataManager {

    fun clearAllPreferenceData() {
        PreferenceManager.clearAllPrefs()
    }

    fun saveArticlesListData(articlesList: ArrayList<ArticleItem>?) {
        val list = Gson().toJson(articlesList)
        PreferenceManager.putString(AppConstants.PreferenceConstantsKeys.KEY_ARTICLES_LIST, list)
    }

    fun getArticlesListData(): ArrayList<ArticleItem>? {
        val type: Type = object : TypeToken<ArrayList<ArticleItem>>() {}.type
        val articlesList: String? =
            PreferenceManager.getString(AppConstants.PreferenceConstantsKeys.KEY_ARTICLES_LIST)
        return Gson().fromJson(articlesList, type)
    }

    fun clearArticlesListData() {
        PreferenceManager.clearViaKey(AppConstants.PreferenceConstantsKeys.KEY_ARTICLES_LIST)
    }
}