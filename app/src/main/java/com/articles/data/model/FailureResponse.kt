package com.articles.data.model

/**
 * Created by {Kapil Sachan} on 22/02/2021.
 */

data class FailureResponse(
    var errorCode: Int? = null,
    var errorMessage: String? = null
) {
    companion object {
        fun genericError(): FailureResponse {
            return FailureResponse(1818, "Something went wrong")
        }
    }
}