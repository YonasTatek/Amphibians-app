package com.example.amphibiansapp.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibiansapp.AmphibianPhotoApp
import com.example.amphibiansapp.data.AmphibiansRepository
import com.example.amphibiansapp.data.AppContainer
import com.example.amphibiansapp.data.DefaultAppContainer
import com.example.amphibiansapp.model.AmphibiansPhoto
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface AmphibianUiState {
    data class Success(val amphibiansPhoto: List<AmphibiansPhoto>) : AmphibianUiState
    object Error : AmphibianUiState
    object Loading : AmphibianUiState

}

class AmphibianViewModel(val amphibiansRepository: AmphibiansRepository) : ViewModel() {
    var amphibianUiState: AmphibianUiState by mutableStateOf(
        AmphibianUiState.Loading
    )
        private set

    init {
        getMarsPhotos()
    }

    fun getMarsPhotos() {
        amphibianUiState = AmphibianUiState.Loading
        viewModelScope.launch {
          amphibianUiState =  try {
              val result = amphibiansRepository.getAmphibiansPhotosAndDescription()
              AmphibianUiState.Success(amphibiansPhoto = result)
            } catch (e: IOException) {
                AmphibianUiState.Error
            } catch (e: HttpException) {
                AmphibianUiState.Error
            }
        }
    }

    companion object
    {
        val factory:ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = (this[APPLICATION_KEY] as AmphibianPhotoApp )
                val amphibiansRepository:AmphibiansRepository  = app.container.amphibiansRepository
                AmphibianViewModel(amphibiansRepository= amphibiansRepository)
            }
        }

    }



}