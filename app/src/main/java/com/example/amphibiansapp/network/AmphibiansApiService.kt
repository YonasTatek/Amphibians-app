package com.example.amphibiansapp.network

import com.example.amphibiansapp.model.AmphibiansPhoto
import retrofit2.http.GET

interface AmphibiansApiService {

    @GET("amphibians")
    suspend fun getPhotos():List<AmphibiansPhoto>
}