package com.arcapp.weatherapp.api

import com.arcapp.weatherapp.constant.Constants
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        // create chain and add api key
        val request: Request = chain.request().newBuilder()
            .url(chain.request().url.newBuilder().addQueryParameter(Constants.API_KEY_NAME, Constants.API_KEY).build())
            .build()
        return chain.proceed(request)

    }

}