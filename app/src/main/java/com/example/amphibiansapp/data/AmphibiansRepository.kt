package com.example.amphibiansapp.data

import com.example.amphibiansapp.model.AmphibiansPhoto
import com.example.amphibiansapp.network.AmphibiansApiService

interface AmphibiansRepository {
    suspend fun getAmphibiansPhotosAndDescription(): List<AmphibiansPhoto>
}

class NetworkAmphibiansRepository(
    private val amphibiansApiService: AmphibiansApiService
) : AmphibiansRepository {
    override suspend fun getAmphibiansPhotosAndDescription(): List<AmphibiansPhoto> =
        amphibiansApiService.getPhotos()
}



