package com.rushabh.gojek.retrofit

import com.rushabh.wipro_rushabh.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitServiceGenerator {

    private val BASE_URL: String = BuildConfig.BASE_URL

    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())

    private val httpClient = OkHttpClient.Builder()

    fun <S> createService(serviceClass: Class<S>?): S {
        builder.client(httpClient.build())
        val retrofit = builder.build()
        return retrofit.create(serviceClass)
    }
}