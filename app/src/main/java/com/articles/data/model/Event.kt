package com.articles.data.model

/**
 * Created by {Kapil Sachan} on 22/02/2021.
 */

class Event<T>(private val content: T) {

    var isAlreadyHandled = false

    fun getContent(): T? {
        if (!isAlreadyHandled) {
            isAlreadyHandled = true
            return content
        }
        return null
    }

    fun peekContent(): T {
        return content
    }
}
