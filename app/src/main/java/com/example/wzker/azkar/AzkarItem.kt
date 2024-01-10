package com.example.wzker.azkar

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import android.os.CombinedVibration
import android.os.VibrationEffect
import android.os.VibratorManager
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationManagerCompat
import com.example.wzker.R
import com.example.wzker.db.ZekrModel
import com.example.wzker.ui.UI.CountTracker

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun AzkarItem(
    modifier: Modifier = Modifier,
    azkar: ZekrModel,
    increaseCurrentCount: (ZekrModel) -> Unit
) {
    var currentCount by rememberSaveable {
        mutableIntStateOf(0)
    }
    val context = LocalContext.current
    val vibrator =
        context.getSystemService(ComponentActivity.VIBRATOR_MANAGER_SERVICE) as VibratorManager
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ),
        modifier = modifier
            .padding(horizontal = 16.dp)
            .width(300.dp)
            .shadow(
                elevation = 15.dp,
                shape = MaterialTheme.shapes.medium
            )
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Text(
                stringResource(id = azkar.primaryZekar),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
            )
            Text(
                stringResource(id = azkar.description),
                style = MaterialTheme.typography.titleSmall.copy(
                    textAlign = TextAlign.Center
                ),
                modifier = modifier.paddingFromBaseline(top = 16.dp, bottom = 20.dp)
            )
            Box(
                modifier = modifier
                    .size(200.dp)
                    .shadow(
                        elevation = 3.dp,
                        shape = CircleShape,
                    )
                    .background(
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                    )
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(color = MaterialTheme.colorScheme.secondary),
                    ) {
                        vibrator.vibrate(
                            CombinedVibration.createParallel(
                                VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE)
                            )
                        )
                        currentCount++
                        increaseCurrentCount(azkar)
                    }
            )


            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                CountTracker(
                    stringResource(id = R.string.allCount),
                    azkar.allCount.toString()
                )
                CountTracker(
                    stringResource(id = R.string.currentCount),
                    currentCount.toString()
                )
            }

        }
    }
}
