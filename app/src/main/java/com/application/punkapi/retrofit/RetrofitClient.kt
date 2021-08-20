package com.application.punkapi.retrofit


import com.application.punkapi.api.ApiService
import com.application.punkapi.helper.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitClient {

    private var apiService: ApiService? = null

    fun getApiService(): ApiService? {

        if (apiService == null) {
            /*UnsafeOkHttpClient - DESACTIVA EL CERTIFICADO - HTTPS*/
            val okHttpClientBuilder =  UnsafeOkHttpClient.getUnsafeOkHttpClient()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)


            okHttpClientBuilder.addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                    val request = chain.request()
                    val newRequest = request.newBuilder()
                    return chain.proceed(newRequest.build())
                }
            })

            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(buildGson()))
                .build()

            apiService = retrofit.create(ApiService::class.java)
        }

        return apiService
    }

    private fun buildGson(): Gson {
        return GsonBuilder()
                .serializeNulls()
                .create()
    }



}