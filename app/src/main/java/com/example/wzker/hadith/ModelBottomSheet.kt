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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.wzker.R
import com.example.wzker.components.ErrorComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HadithBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: (Boolean) -> Unit,
    anotherHadith: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    val viewModel: HadithViewModel = viewModel()
    val uiState by viewModel.hadithUiState.collectAsStateWithLifecycle()
    val compositionLoading by
    rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading))
    val compositionError by
    rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.error))
    val context = LocalContext.current
    val progressError by animateLottieCompositionAsState(composition = compositionError)

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
            is HadithErrorState -> {
                ErrorComponent(message = (uiState.state as HadithErrorState).message)
            }

            is HadithExceptionState -> {

            }

            HadithInitializationState -> {
            }

            HadithLoadingState -> {
                LottieAnimation(
                    composition = compositionLoading,
                    restartOnPlay = true,
                    iterations = 5,
                )
            }

            is HadithSuccessState -> {
                val hadithState = uiState.state as HadithSuccessState
                ShowContentOfHadith(
                    text = hadithState.hadith,
                    shareHadith = {
                        val intent = Intent(Intent.ACTION_SEND).apply {
                            putExtra(Intent.EXTRA_TEXT, hadithState.hadith)
                            type = "text/plain"
                        }
                        startActivity(context, intent, Bundle())
                    },
                    getAnotherHadith = {
                        anotherHadith()
                    },
                )
            }
        }

    }
}