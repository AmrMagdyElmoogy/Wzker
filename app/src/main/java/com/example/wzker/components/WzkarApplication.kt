package com.example.wzker.components

import android.app.Application
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import com.example.wzker.notification.NotificationJobService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WzkarApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val componentName = ComponentName(applicationContext, NotificationJobService::class.java)
        val jobInfo =
            JobInfo.Builder(1, componentName)
                .setPeriodic(30 * 60 * 1000)
                .setPersisted(true)
                .build()
        val jobScheduler =
            applicationContext.getSystemService(JobScheduler::class.java) as JobScheduler
        jobScheduler.schedule(jobInfo)
    }
}