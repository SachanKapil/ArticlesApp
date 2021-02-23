package com.articles

import android.app.Application
import android.content.Context


/**
 * Created by {Kapil Sachan} on 22/02/2021.
 */

class ArticlesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object {
        lateinit var appContext: Context
    }
}
