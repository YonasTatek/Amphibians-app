package com.example.amphibiansapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibiansapp.R
import com.example.amphibiansapp.model.AmphibiansPhoto
import com.example.amphibiansapp.ui.theme.AmphibiansAppTheme

val demo_sample = listOf<AmphibiansPhoto>(

    AmphibiansPhoto(
        name = "Great Basin Spadefoot",
        type = "Toad",
        description = "This toad spends most of its life underground due to the arid desert conditions in which it lives. Spadefoot toads earn the name because of their hind legs which are wedged to aid in digging. They are typically grey, green, or brown with dark spots.",
        imgSrc = "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/great-basin-spadefoot.png"
    ),
    AmphibiansPhoto(
        name = "Great Basin Spadefoot",
        type = "Toad",
        description = "This toad spends most of its life underground due to the arid desert conditions in which it lives. Spadefoot toads earn the name because of their hind legs which are wedged to aid in digging. They are typically grey, green, or brown with dark spots.",
        imgSrc = "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/great-basin-spadefoot.png"
    ),
    AmphibiansPhoto(
        name = "Great Basin Spadefoot",
        type = "Toad",
        description = "This toad spends most of its life underground due to the arid desert conditions in which it lives. Spadefoot toads earn the name because of their hind legs which are wedged to aid in digging. They are typically grey, green, or brown with dark spots.",
        imgSrc = "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/great-basin-spadefoot.png"
    ),
    AmphibiansPhoto(
        name = "Great Basin Spadefoot",
        type = "Toad",
        description = "This toad spends most of its life underground due to the arid desert conditions in which it lives. Spadefoot toads earn the name because of their hind legs which are wedged to aid in digging. They are typically grey, green, or brown with dark spots.",
        imgSrc = "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/great-basin-spadefoot.png"
    )
)


@Composable

fun HomeScreen(modifier: Modifier = Modifier) {
    val amphibianViewModel: AmphibianViewModel = viewModel(factory = AmphibianViewModel.factory)
    when (val amphibianUiState = amphibianViewModel.amphibianUiState) {
        is AmphibianUiState.Success -> ListAmphibiansCardPhoto(photos = amphibianUiState.amphibiansPhoto)
        is AmphibianUiState.Loading -> LoadingScreen()
        is AmphibianUiState.Error -> ErrorScreen(retryAction = { amphibianViewModel.getMarsPhotos() })
    }


}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = stringResource(id = R.string.error_loading))
        Button(onClick = retryAction) {
            Text(text = stringResource(id = R.string.retry))
        }

    }


}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.loading), contentDescription = stringResource(
                id = R.string.app_name
            )
        )
    }
}

@Composable
fun ListAmphibiansCardPhoto(modifier: Modifier = Modifier, photos: List<AmphibiansPhoto>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        items(photos) {
            AmphibiansCardPhoto(amphibiansPhoto = it)
        }
    }

}


@Composable
fun AmphibiansCardPhoto(amphibiansPhoto: AmphibiansPhoto, modifier: Modifier = Modifier) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        elevation = 8.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = String.format("%s (%s)", amphibiansPhoto.name, amphibiansPhoto.type),
                style = MaterialTheme.typography.h6
            )
            Text(
                text = amphibiansPhoto.description,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amphibiansPhoto.imgSrc)
                    .crossfade(true)
                    .build(),
                modifier = Modifier.fillMaxSize(),
                error = painterResource(id = R.drawable.error_loading),
                placeholder = painterResource(id = R.drawable.loading),
                contentDescription = amphibiansPhoto.name,
                contentScale = ContentScale.FillBounds
            )


        }
    }
}


@Preview
@Composable

fun AmphibiansCardPhotoPreview() {
    AmphibiansAppTheme() {
        AmphibiansCardPhoto(amphibiansPhoto = demo_sample[0])
    }
}


