package com.example.wzker.quran

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.wzker.R
import com.example.wzker.components.ErrorComponent
import java.text.NumberFormat
import java.util.Locale

@Composable
fun QuranScreen(modifier: Modifier = Modifier) {
    val viewModel: QuranViewModel = viewModel()
    val uiState by viewModel.quranUiState.collectAsStateWithLifecycle()

    val compositionOfLoading by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(
            R.raw.loading
        )
    )
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 30.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

//        QuranSearchBar() {}
        when (uiState.state) {
            is QuranErrorState -> {
                val error = uiState.state as QuranErrorState
                ErrorComponent(message = error.message)
            }

            is QuranExceptionState -> {
                val error = uiState.state as QuranExceptionState
                ErrorComponent(message = error.message)

            }

            QuranInitializationState -> {}
            QuranLoadingState -> {
                LottieAnimation(
                    composition = compositionOfLoading,
                    restartOnPlay = true
                )
            }

            is QuranSuccessState -> {
                val successState = uiState.state as QuranSuccessState
                QuranCardAya(state = successState)
                QuranCardTafseer(tafseer = successState.tafseer)

            }

            QuranInternetLost -> {
                ErrorComponent(message = stringResource(id = R.string.noInternet))

            }
        }
        ElevatedButton(
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            ),
            onClick = {
                viewModel.getAnotherAyah()
            },
            modifier = modifier
                .padding(16.dp)
        ) {
            Text(stringResource(R.string.another_aya), style = MaterialTheme.typography.bodyMedium)
        }
    }
}

fun String.appendAyahSymbol(number: Int): String {
    val n = NumberFormat.getInstance(Locale.forLanguageTag("AR"))
    return this + n.format(number)
}