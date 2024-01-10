package com.example.wzker.components

import android.app.AlarmManager
import android.app.Application
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import com.example.wzker.db.ZekrDatabase
import com.example.wzker.hadith.HadithRepository
import com.example.wzker.notification.NotificationJobService
import com.example.wzker.quran.QuranRepository

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
        ZekrDatabase.initialize(applicationContext)
        HadithRepository.initialize()
        QuranRepository.initialize()
    }
}