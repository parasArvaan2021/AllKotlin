package com.main.kotlin

import android.app.Notification
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Parcelable
import android.util.Log
import com.main.kotlin.Notification.Companion.NOTIFICATION_CHANNEL_ID


class MyScheduleNotification : BroadcastReceiver() {

    companion object{
        var NOTIFICATION_ID = "notification-id"
        var NOTIFICATION = "notification"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i("paras", "onReceive:dsfsdg ")
        val notificationManager :NotificationManager= context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification: Notification? = intent?.getParcelableExtra(NOTIFICATION)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance: Int = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel =NotificationChannel(NOTIFICATION_CHANNEL_ID,"NOTIFICATION_CHANNEL_NAME" , importance)
            assert(notificationManager != null)
            notificationManager!!.createNotificationChannel(notificationChannel)
        }
        val id: Int? = intent?.getIntExtra(NOTIFICATION_ID, 0)
        assert(notificationManager != null)
        if (id != null) {
            notificationManager.notify(id, notification)
        }
    }
}