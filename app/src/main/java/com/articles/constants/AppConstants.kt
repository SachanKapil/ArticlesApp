package com.articles.constants

/**
 * Created by {Kapil Sachan} on 22/02/2021.
 */

object AppConstants {

    object BundleConstants {
        const val KEY_ARTICLE_ITEM = "key_article_item"
    }

    object NetworkingConstants {
        const val INTERNAL_SERVER_ERROR_CODE = 500
        const val NO_INTERNET_CONNECTION = 999
    }

    object ApiErrorConstants {
        const val API_ERROR_CODE_INVALID_TOKEN = 419
        const val API_ERROR_CODE_SESSION_EXPIRED = 420
    }

    object PreferenceConstantsKeys {
        const val KEY_ARTICLES_LIST = "key_articles_list"
    }

    object ScreenConstants {
        const val OPEN_HOME_SCREEN = 1
    }

    object DateTimeFormatConstants {
        const val DATE_API_FORMAT = "YYYY-MM-dd HH:mm:ss"
        const val DATE_FORMAT_MMM_DD_YYYY = "MMM dd, YYYY"
        const val TIME_FORMAT_HH_MM_A = "hh:mm a"
    }

    object ViewTypeConstants {
        //common adapter view constants
        const val VIEW_TYPE_ARTICLE_BIG = 1
        const val VIEW_TYPE_ARTICLE_SMALL = 2
    }
}
