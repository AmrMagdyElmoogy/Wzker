package com.example.wzker.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.wzker.R

interface AskarDestination {
    @get:DrawableRes
    val icon: Int

    @get:StringRes
    val title: Int
    val route: String
}

object AzkarScreen : AskarDestination {
    override val icon: Int
        get() = R.drawable.beads
    override val title: Int
        get() = R.string.alazkar
    override val route: String
        get() = "Wzker/azkar"
}

object HadithScreen : AskarDestination {
    override val icon: Int
        get() = R.drawable.pray
    override val title: Int
        get() = R.string.hadith
    override val route: String
        get() = "Wzkar/hadith"
}

object QuranScreen : AskarDestination {
    override val icon: Int
        get() = R.drawable.quran
    override val title: Int
        get() = R.string.quran
    override val route: String
        get() = "Wzkar/quran"

}
