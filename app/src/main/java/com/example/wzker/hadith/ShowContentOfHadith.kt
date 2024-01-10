package com.example.wzker.hadith

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.wzker.R
import com.example.wzker.components.CardComponent

@Composable
fun ShowContentOfHadith(
    text: String,
    modifier: Modifier = Modifier,
    shareHadith: () -> Unit,
    getAnotherHadith: () -> Unit
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CardComponent(text = text)
        Row(
            modifier = modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    getAnotherHadith()
                },
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.refresh),
                    contentDescription = null,
                    modifier = modifier.size(24.dp)
                )
            }
            IconButton(
                onClick = {
                    shareHadith()
                }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.share),
                    contentDescription = null,
                    modifier = modifier.size(24.dp)
                )
            }
        }
    }
}