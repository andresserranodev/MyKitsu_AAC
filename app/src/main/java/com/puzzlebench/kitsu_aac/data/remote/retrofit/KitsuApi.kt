package com.puzzlebench.kitsu_aac.data.remote.retrofit

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface KitsuApi {
    @GET("api/edge/anime")
    suspend fun getAnime(
        @Query("page[limit]") limit: Int,
        @Query("page[offset]") offset: Int
    ): KitsuResponse

    companion object FactoryYelpApi {

        private fun getOkHttpClientBuilder(): OkHttpClient.Builder =
            OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())

        fun makeServiceKitsuApi(): KitsuApi {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClientBuilder().build())
                .baseUrl("https://kitsu.io/")
                .build()
            return retrofit.create(KitsuApi::class.java)
        }
    }
}
