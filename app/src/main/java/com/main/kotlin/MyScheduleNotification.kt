package com.main.kotlin

import android.app.Notification
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import com.main.kotlin.NotificationClass.Companion.NOTIFICATION_CHANNEL_ID


class MyScheduleNotification : BroadcastReceiver() {

    companion object {
        var NOTIFICATION_ID = "notification-id"
        var NOTIFICATION = "notification"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i("paras", "onReceive:dsfsdg ")
        val not = NotificationClass()
        not.sendAndRequestResponse(context)
        val notificationManager: NotificationManager =
            context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification: Notification? = intent?.getParcelableExtra(NOTIFICATION)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance: Int = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "NOTIFICATION_CHANNEL_NAME",
                importance
            )

            notificationManager.createNotificationChannel(notificationChannel)
        }
        val id: Int? = intent?.getIntExtra(NOTIFICATION_ID, 0)
        if (id != null) {
            notificationManager.notify(id, notification)
        }
    }
}
