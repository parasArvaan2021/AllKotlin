package com.main.kotlin

import android.app.Notification
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import java.util.*


class MyScheduleNotification : BroadcastReceiver() {

    companion object {
        var NOTIFICATION_ID = "notification-id"
        var NOTIFICATION_NAME = "NOTIFICATION_CHANNEL_NAME"
        var NOTIFICATION = "notification"
        val NOTIFICATION_CHANNEL_ID = "10001"
        val default_notification_channel_id = "default"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i("paras", "onReceive:dsfsdg ")
        val not = GenerateNotification()
        not.sendAndRequestResponse(context)
        val notificationManager: NotificationManager =
            context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification: Notification? = intent?.getParcelableExtra(NOTIFICATION)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance: Int = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_NAME,
                importance
            )

            notificationManager.createNotificationChannel(notificationChannel)
        }
//        try {

        val min = 20
        val max = 80
        val random: Int = Random().nextInt(max - min + 1) + min

        val id = random
        Log.i(
            "paras",
            "onReceive: $id --> ${intent?.getStringExtra(MyScheduleNotification.NOTIFICATION_ID)}"
        )
        notificationManager.notify(id, notification)
//        } catch (e: NumberFormatException) {
//
//        }
    }
}
