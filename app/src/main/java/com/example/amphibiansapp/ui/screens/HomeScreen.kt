package com.example.amphibiansapp.ui.screens

import android.view.Display
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable

fun HomeScreen() {
    val viewModel: AmphibianViewModel = viewModel()
    when (viewModel.amphibianUiState) {
        is AmphibianUiState.Success -> Display(viewModel.amphibianUiState.toString())
        is AmphibianUiState.Loading -> Welcome(onClick = { viewModel.getMarsPhotos() })
        else -> Welcome(onClick = { viewModel.getMarsPhotos() })
    }

}

@Composable
fun Display(text: String) {
    Text(text = text)
}

@Composable
fun Loading() {
    Text(text = "loading")
}

@Composable
fun Welcome(onClick: () -> Unit) {

    Button(onClick = onClick) {
        Text(text = "Load")
    }
}