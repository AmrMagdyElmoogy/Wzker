package com.example.wzker.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.wzker.R
import com.example.wzker.components.AzkarScreen

class NotificationReciever : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val zekr: Int? = intent?.getIntExtra("Zekr", R.string.azkar_1)

        when (zekr) {
            R.string.azkar_1 -> {


            }

            R.string.azkar_2 -> {
            }


            R.string.azkar_3 -> {
            }


            R.string.azkar_4 -> {
            }


            R.string.azkar_5 -> {
            }
        }


    }

}