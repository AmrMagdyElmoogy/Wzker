package com.example.wzker.quran

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.wzker.R

@Composable
fun QuranCardAya(modifier: Modifier = Modifier, state: QuranSuccessState) {
    Card(
        modifier = modifier.padding(vertical = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF2E9D9),
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box() {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.sura_border),
                    contentDescription = null,
                )

                Text(
                    state.surah_name,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = modifier
                        .paddingFromBaseline(bottom = 30.dp)
                        .align(Alignment.TopCenter)
                )
            }
            Text(
                state.surah.appendAyahSymbol(state.surah_number),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}