package com.mertadali.artsharer.api

import com.mertadali.artsharer.model.ImageResponse
import com.mertadali.artsharer.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

// Retrofit ile verileri çekeceğiz.

interface RetrofitAPI {

    @GET(value = "/api/")
    suspend fun imageSearch(
        @Query(value = "q") searchQuery: String,
        @Query(value = "key") apiKey : String = API_KEY
    ) : retrofit2.Response<ImageResponse>
}