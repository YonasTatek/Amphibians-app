package com.example.amphibiansapp.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

class AmphibianViewModel : ViewModel() {
    var amphibianUiState: AmphibianUiState by mutableStateOf(
        AmphibianUiState.Loading
    )
        private set

    fun getMarsPhotos() {
        viewModelScope.launch {
          amphibianUiState =  try {
                val network: AppContainer = DefaultAppContainer()
                val result = network.amphibiansRepository.getAmphibiansPhotosAndDescription()
                AmphibianUiState.Success(result)
            } catch (e: IOException) {
                AmphibianUiState.Error
            } catch (e: HttpException) {
                AmphibianUiState.Error
            }
        }
    }


}