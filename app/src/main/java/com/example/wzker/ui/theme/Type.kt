package com.example.wzker.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.wzker.R


private val amiriFontFamily = FontFamily(
    Font(R.font.uthmanic_hafs_v22)
)


private val shezFontFamily = FontFamily(
    Font(R.font.schez_regular, weight = FontWeight.Normal),
    Font(R.font.schez_medium, weight = FontWeight.Medium),
    Font(R.font.schez_bold, weight = FontWeight.Bold),
)


// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = shezFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 25.sp,
        lineHeight = 20.sp,
        textAlign = TextAlign.Start,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = shezFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 20.sp,
        textAlign = TextAlign.Start,
        letterSpacing = 0.5.sp
    ),

    bodySmall = TextStyle(
        fontFamily = shezFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 20.sp,
        textAlign = TextAlign.Start,
        letterSpacing = 0.5.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = shezFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        lineHeight = 24.sp,
        textAlign = TextAlign.Center,
        letterSpacing = 0.5.sp
    ),
    titleMedium = TextStyle(
        fontFamily = amiriFontFamily,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Center,
        fontSize = 28.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = amiriFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        textAlign = TextAlign.Center,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
)