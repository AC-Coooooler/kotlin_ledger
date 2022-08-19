package com.hhyhhy.ledger.api.response

data class Response<T>(val data: T?, val code: Int = 0, val msg: String = "OK")

typealias EmptyResponse = Response<Any>

fun emptyResponse(): EmptyResponse = Response(null)
