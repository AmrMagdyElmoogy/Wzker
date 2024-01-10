package com.example.wzker.hadith

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wzker.R

enum class Books(@StringRes val resource: Int) {
    MUSLIM(R.string.muslim),
    BUKHARI(R.string.bukhari),
    TIRMIDZI(R.string.tirmidzi),
    NASAI(R.string.nasai),
    ABU_DAUD(R.string.abu_daud),
    IBNU_MAJAH(R.string.ibnu_majah),
    MALIK(R.string.malik)
}

@Composable
fun HadithScreen(modifier: Modifier = Modifier) {
    val viewModel: HadithViewModel = viewModel()
    var showModelSheet by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            stringResource(id = R.string.choooseYourHadith),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onBackground)
        )

        LazyVerticalGrid(
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.Center,
            columns = GridCells.Fixed(2),
        ) {
            itemsIndexed(
                Books.entries
            ) { i, it ->
                if (i < Books.entries.lastIndex) {
                    HadithItem(
                        item = it,

                    ) { book ->
                        viewModel.getHadith(book.name)
                        showModelSheet = true
                    }
                }
            }
        }
        HadithItem(
            item = Books.entries.last(),
            modifier = modifier.fillMaxWidth()
        ) { book ->
            viewModel.getHadith(book.name)
            showModelSheet = true
        }
        AnimatedVisibility(visible = showModelSheet) {
            HadithBottomSheet(
                onDismiss = {
                    showModelSheet = it
                },
                anotherHadith = {
                    viewModel.getHadith(viewModel.currentSelectedBook)
                }
            )
        }


    }
}