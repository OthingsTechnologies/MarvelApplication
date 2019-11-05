package com.emmanuel.martinez.rodriguez.core

import com.emmanuel.martinez.rodriguez.core.utils.Constants.BASE_URL
import com.emmanuel.martinez.rodriguez.core.utils.Constants.CONNECTION_TIMEOUT
import com.emmanuel.martinez.rodriguez.core.utils.Constants.READ_TIMEOUT
import com.emmanuel.martinez.rodriguez.core.utils.Constants.WRITE_TIMEOUT
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object API {

    private var retrofit: Retrofit? = null

    val apiService: Retrofit?
        get() {

            if (retrofit == null) {

                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                val client = OkHttpClient.Builder()
                    .connectTimeout(CONNECTION_TIMEOUT,TimeUnit.SECONDS)
                    .readTimeout(READ_TIMEOUT,TimeUnit.SECONDS)
                    .writeTimeout(WRITE_TIMEOUT,TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .build()

                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()

            }

            return retrofit

        }


}
