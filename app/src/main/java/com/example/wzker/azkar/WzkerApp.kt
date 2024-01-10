package com.example.wzker.azkar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wzker.R
import com.example.wzker.components.AzkarScreen
import com.example.wzker.components.HadithScreen
import com.example.wzker.components.QuranScreen
import com.example.wzker.hadith.HadithScreen
import com.example.wzker.quran.QuranScreen

@Composable
fun WzkerApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    var titleAppBar by remember {
        mutableIntStateOf(R.string.azkar)
    }
    Scaffold(
        topBar = {
            AzkarAppBar(title = titleAppBar)
        },
        bottomBar = {
            AzkarNavigationBar(
                onAzkarClicked = {
                    navController.navigateTo(it)
                    titleAppBar = R.string.azkar
                },
                onHadithClicked = {
                    navController.navigateTo(it)
                    titleAppBar = R.string.hadithEveryDay
                },
                onQuranClicked = {
                    navController.navigateTo(it)
                    titleAppBar = R.string.ayaTadapor
                },
            )
        },
        containerColor = MaterialTheme.colorScheme.primary,
        modifier = modifier
            .fillMaxSize()
    ) { paddingValues ->
        Box(
            modifier = modifier
                .padding(paddingValues)
                .padding(8.dp)
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(
                        25.dp
                    )
                )
                .fillMaxHeight()
        ) {

            NavHost(navController = navController, startDestination = AzkarScreen.route) {
                composable(AzkarScreen.route) {
                    AzkarSelection()
                }
                composable(HadithScreen.route) {
                    HadithScreen()
                }

                composable(QuranScreen.route) {
                    QuranScreen()
                }

            }
        }

    }
}

fun NavHostController.navigateTo(route: String) {
    navigate(route) {
        popUpTo(
            this@navigateTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}