package com.example.wzker.azkar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.animation.content.RoundedCornersContent
import com.example.wzker.R
import com.example.wzker.components.AzkarScreen
import com.example.wzker.components.HadithScreen
import com.example.wzker.components.QuranScreen

@Composable
fun AzkarNavigationBar(
    modifier: Modifier = Modifier,
    onAzkarClicked: (String) -> Unit,
    onHadithClicked: (String) -> Unit,
    onQuranClicked: (String) -> Unit,
) {
    var currentRoute by remember {
        mutableStateOf(AzkarScreen.route)
    }

    NavigationBar(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(25.dp))
    ) {
        AzkarNavigationItem(
            modifier = modifier.weight(1f),
            route = AzkarScreen.route,
            currentRoute = currentRoute,
            icon = R.drawable.beads,
            title = R.string.alazkar,
            onClick = {
                when (it) {
                    AzkarScreen.route -> {
                        onAzkarClicked(AzkarScreen.route)
                        currentRoute = AzkarScreen.route
                    }

                    HadithScreen.route -> {
                        onHadithClicked(HadithScreen.route)
                        currentRoute = HadithScreen.route
                    }

                    QuranScreen.route -> {
                        onQuranClicked(QuranScreen.route)
                        currentRoute = QuranScreen.route
                    }
                }
            }
        )
        AzkarNavigationItem(
            modifier = modifier.weight(1f),
            route = HadithScreen.route,
            currentRoute = currentRoute,
            icon = R.drawable.pray,
            title = R.string.hadith,
            onClick = {
                when (it) {
                    AzkarScreen.route -> {
                        onAzkarClicked(AzkarScreen.route)
                        currentRoute = AzkarScreen.route
                    }

                    HadithScreen.route -> {
                        onHadithClicked(HadithScreen.route)
                        currentRoute = HadithScreen.route
                    }

                    QuranScreen.route -> {
                        onQuranClicked(QuranScreen.route)
                        currentRoute = QuranScreen.route
                    }
                }
            }
        )
        AzkarNavigationItem(
            modifier = modifier.weight(1f),
            route = QuranScreen.route,
            currentRoute = currentRoute,
            icon = R.drawable.quran,
            title = R.string.quran,
            onClick = {
                when (it) {
                    AzkarScreen.route -> {
                        onAzkarClicked(AzkarScreen.route)
                        currentRoute = AzkarScreen.route
                    }

                    HadithScreen.route -> {
                        onHadithClicked(HadithScreen.route)
                        currentRoute = HadithScreen.route
                    }

                    QuranScreen.route -> {
                        onQuranClicked(QuranScreen.route)
                        currentRoute = QuranScreen.route
                    }
                }
            }
        )
    }
}