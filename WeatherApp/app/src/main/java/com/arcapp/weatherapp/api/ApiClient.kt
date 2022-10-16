package com.arcapp.retrofitexample.api

import com.arcapp.weatherapp.api.AuthInterceptor
import com.arcapp.weatherapp.constant.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ApiClient {

    companion object{

        private lateinit var apiService: ApiService

        // If Api Service object is not created, we create and send a new one.
        fun getApiService() : ApiService{

            if(!::apiService.isInitialized){
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.API_ADDRESS)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getHttpClient())
                    .build()

                apiService = retrofit.create(ApiService::class.java)

            }

            return apiService

        }

        // We add Timeout and AuthInterceptor objects to OkHttpClient and add apiService
        private fun getHttpClient(): OkHttpClient {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(AuthInterceptor())
            httpClient.connectTimeout(60, TimeUnit.SECONDS)
            httpClient.readTimeout(60, TimeUnit.SECONDS)
            httpClient.writeTimeout(90, TimeUnit.SECONDS)
            return httpClient.build()
        }

    }

}