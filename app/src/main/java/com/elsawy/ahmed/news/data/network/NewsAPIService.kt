package com.elsawy.ahmed.news.data.network

import com.elsawy.ahmed.news.data.Entity.NewsResponse
import com.elsawy.ahmed.news.data.network.internet_connection.ConnectivityInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY : String = "1656a19191114687b19459ff60ecd0a6"

interface NewsAPIService {

    @GET("top-headlines")
    fun getTopNewsRequestAsync(
        @Query("country") country: String,
        @Query("category") category: String
    ):Deferred<NewsResponse>

    @GET("everything")
    fun getEveryNewsRequestAsync(
        @Query("qInTitle") query: String,
        @Query("sortBy") sortBy: String,
        @Query("from") uploadDate: String
    ):Deferred<NewsResponse>


    companion object{
        operator fun invoke(connectivityInterceptor : ConnectivityInterceptor) : NewsAPIService {
            val requestInterceptor = Interceptor{chain ->
                val  url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("apiKey",
                        API_KEY
                    )
                    .build()

                val  request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()


            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://newsapi.org/v2/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsAPIService::class.java)
        }
    }

}