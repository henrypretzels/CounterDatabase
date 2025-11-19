package com.example.counterdatabase.data

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private const val CACHE_SIZE = 10 * 1024 * 1024L
    private const val CONNECT_TIMEOUT = 30L
    private const val READ_TIMEOUT = 30L
    private const val WRITE_TIMEOUT = 30L

    @Volatile
    private var retrofit: Retrofit? = null

    fun initialize(context: Context) {
        if (retrofit == null) {
            synchronized(this) {
                if (retrofit == null) {
                    try {
                        val cacheDir = File(context.cacheDir, "http_cache")
                        val cache = Cache(cacheDir, CACHE_SIZE)

                        val loggingInterceptor = HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }

                        val client = OkHttpClient.Builder()
                            .cache(cache)
                            .addInterceptor(loggingInterceptor)
                            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                            .retryOnConnectionFailure(true)
                            .build()

                        retrofit = Retrofit.Builder()
                            .baseUrl("https://raw.githubusercontent.com/ByMykel/CSGO-API/main/public/api/en/")
                            .client(client)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                    } catch (e: Exception) {
                        android.util.Log.e("RetrofitInstance", "Error initializing Retrofit", e)
                        retrofit = Retrofit.Builder()
                            .baseUrl("https://raw.githubusercontent.com/ByMykel/CSGO-API/main/public/api/en/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                    }
                }
            }
        }
    }

    val api: ApiService
        get() {
            if (retrofit == null) {
                android.util.Log.w("RetrofitInstance", "Retrofit not initialized, creating default instance")
                retrofit = Retrofit.Builder()
                    .baseUrl("https://raw.githubusercontent.com/ByMykel/CSGO-API/main/public/api/en/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!.create(ApiService::class.java)
        }
}