package com.articles.data.model

/**
 * Created by {Kapil Sachan} on 22/02/2021.
 */

data class WrappedResponse<T>(
    var data: T? = null,
    var failureResponse: FailureResponse? = null
)