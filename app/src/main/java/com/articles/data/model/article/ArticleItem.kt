package com.articles.data.model.article


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticleItem(
    @SerializedName("author")
    var author: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("enclosure")
    var enclosure: Enclosure? = null,
    @SerializedName("pubDate")
    var pubDate: String? = null,
    @SerializedName("thumbnail")
    var thumbnail: String? = null,
    @SerializedName("title")
    var title: String? = null
) : Parcelable {
    @Parcelize
    data class Enclosure(
        @SerializedName("link")
        var link: String? = null,
        @SerializedName("thumbnail")
        var thumbnail: String? = null
    ) : Parcelable
}