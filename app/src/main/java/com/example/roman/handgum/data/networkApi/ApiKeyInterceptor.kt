package com.example.roman.handgum.data.networkApi

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * @author rofor
 */
class ApiKeyInterceptor constructor(var apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestWithoutApiKey: Request = chain.request()

        val url: HttpUrl = requestWithoutApiKey.url
            .newBuilder()
            .addQueryParameter(PARAM_API_KEY, apiKey)
            .build()

        val requestWithAttachedApiKey: Request = requestWithoutApiKey.newBuilder()
            .url(url)
            .build()

        return chain.proceed(requestWithAttachedApiKey)
    }

    companion object {
        private const val PARAM_API_KEY = "api-key"
        fun create(apiKey: String): Interceptor {
            return ApiKeyInterceptor(apiKey)
        }
    }
}