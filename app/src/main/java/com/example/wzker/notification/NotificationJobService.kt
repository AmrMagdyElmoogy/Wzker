package com.example.wzker.notification

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.wzker.util.ids
import kotlin.random.Random

class NotificationJobService : JobService() {
    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d("onStartJob", "Starting")
        val index = Random.nextInt(5)
        val title = getRandomZekr(applicationContext, index)
        val song = getSoundOfRandomZekr(index)

        val listOfChannelsAndBuilders =
            createPairOfChannelAndBuilder(applicationContext, title, song)

        val builder = listOfChannelsAndBuilders[index].first
        val notificationManager =
            NotificationManagerCompat.from(applicationContext)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(listOfChannelsAndBuilders[index].second)
        }

        notificationManager.notify(index + 1, builder.build())
        return false
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return false
    }

}

@RequiresApi(Build.VERSION_CODES.O)
fun createPairOfChannelAndBuilder(
    context: Context,
    title: String,
    song: Int
): List<Pair<NotificationCompat.Builder, NotificationChannel>> {
    val list = mutableListOf<Pair<NotificationCompat.Builder, NotificationChannel>>()
    for (i in 0..4) {
        val builder = createNotificationBuilder(context, title, ids[i], song)
        val channel = createNotificationChannel(i, context)
        list.add(Pair(builder, channel))
    }
    return list.toList()
}

@RequiresApi(Build.VERSION_CODES.O)
private fun createNotificationChannel(i: Int, context: Context): NotificationChannel =
    NotificationChannel(
        ids[i],
        "Channel #${i + 1}",
        NotificationManager.IMPORTANCE_HIGH
    ).apply {
        setSound(
            Uri.parse(
                "android.resource://" + context.packageName + "/" + getSoundOfRandomZekr(i)
            ),
            AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE)
                .build()
        )
    }