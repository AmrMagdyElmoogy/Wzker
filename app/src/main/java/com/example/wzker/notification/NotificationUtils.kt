package com.example.wzker.notification

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.app.NotificationCompat
import com.example.wzker.MainActivity
import com.example.wzker.R
import kotlin.random.Random

val listOfAzkar = listOf(
    Pair(R.string.azkar_1, R.raw.notification_sound_3),
    Pair(R.string.azkar_2, R.raw.notification_sound_1),
    Pair(R.string.azkar_3, R.raw.notification_sound_4),
    Pair(R.string.azkar_4, R.raw.notification_sound_2),
    Pair(R.string.azkar_5, R.raw.notification_sound_5),
)

fun createNotificationBuilder(
    context: Context,
    title: String,
    id: String,
    song: Int
): NotificationCompat.Builder {
    val intent = Intent(context, MainActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    val pendingIntent = PendingIntent.getActivity(
        context,
        0,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
    return NotificationCompat.Builder(context, id)
        .setSmallIcon(R.mipmap.ic_launcher_new_round)
        .setContentTitle("Wzker")
        .setContentText(title)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setAutoCancel(true)
        .setSound(
            Uri.parse(
                "android.resource://" + context.packageName + "/" + song
            )
        )
        .addAction(
            R.drawable.pray,
            "تم",
            pendingIntent
        )

}

fun getRandomZekr(context: Context, index: Int): String =
    context.getString(listOfAzkar[index].first)

fun getSoundOfRandomZekr(index: Int): Int =
    listOfAzkar[index].second