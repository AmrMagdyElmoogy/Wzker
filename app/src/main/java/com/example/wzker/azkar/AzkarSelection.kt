package com.example.wzker.azkar

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wzker.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AzkarSelection(
    modifier: Modifier = Modifier,
    viewModel: AzkarViewModel = viewModel(),
) {
    val data by viewModel.askar.collectAsStateWithLifecycle()

    LazyRow(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxHeight()
    ) {
        items(
            items = data,
            key = { zekr -> zekr.id },
            itemContent = {
                AzkarItem(
                    azkar = it,
                    modifier = modifier
                        .animateItemPlacement(
                            animationSpec = tween(
                                durationMillis = 500,
                                easing = LinearOutSlowInEasing,
                            )
                        ),
                ) { azkar ->
                    val oldAllCount = azkar.allCount
                    val newZekr = azkar.copy(
                        allCount = oldAllCount + 1
                    )
                    viewModel.increaseCount(newZekr)
                }
            }
        )
    }
}