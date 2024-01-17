package com.example.wzker.hadith

import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.wzker.R
import com.example.wzker.components.ErrorComponent
import com.example.wzker.util.ErrorNotException
import com.example.wzker.util.IOException
import com.example.wzker.util.Loading
import com.example.wzker.util.RetrofitException
import com.example.wzker.util.Success

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HadithBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: (Boolean) -> Unit,
    anotherHadith: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    val viewModel: HadithViewModel = hiltViewModel()
    val uiState by viewModel.hadithUiState.collectAsStateWithLifecycle()
    val compositionLoading by
    rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading))
    rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.error))
    val context = LocalContext.current

    ModalBottomSheet(
        sheetState = sheetState,
        modifier = modifier.heightIn(120.dp),
        shape = RoundedCornerShape(16.dp),
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        onDismissRequest = {
            onDismiss(false)
        }) {

        when (uiState.state) {
            is ErrorNotException -> {
                ErrorComponent(message = (uiState.state as ErrorNotException).message)
            }

            is IOException -> {
                ErrorComponent(message = (uiState.state as IOException).message)

            }

            Loading -> {
                LottieAnimation(
                    composition = compositionLoading,
                    restartOnPlay = true,
                    iterations = 10,
                )
            }

            is RetrofitException -> {
                ErrorComponent(message = (uiState.state as RetrofitException).message)

            }

            is Success<*> -> {
                val hadithState = uiState.state as Success<*>
                ShowContentOfHadith(
                    text = hadithState.data as String,
                    shareHadith = {
                        val intent = Intent(Intent.ACTION_SEND).apply {
                            putExtra(Intent.EXTRA_TEXT, hadithState.data)
                            type = "text/plain"
                        }
                        startActivity(context, intent, Bundle())
                    },
                    getAnotherHadith = {
                        anotherHadith()
                    },
                )
            }

            else -> {}
        }

    }
}