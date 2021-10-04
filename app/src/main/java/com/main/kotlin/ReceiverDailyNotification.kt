package com.main.kotlin

import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class ReceiverDailyNotification : BroadcastReceiver() {
    companion object {
        var NOTIFICATION_ID = "notification-id"
        var NOTIFICATION_NAME = "NOTIFICATION_CHANNEL_NAME"
        var NOTIFICATION = "notification"
        val NOTIFICATION_CHANNEL_ID = "10001"
        val default_notification_channel_id = "default"
    }

    override fun onReceive(contex: Context?, intent: Intent?) {
        val not = GenerateNotification()

        val date =
            SimpleDateFormat("yyyy-MM-dd 00:00:00", Locale.getDefault()).format(Date())
        val calDate = not.setAlarmCalender(date)
        calDate.add(Calendar.DAY_OF_MONTH, 1)
        Log.i("date", "onClick:   ${calDate.time}")
        intent?.putExtra("Notification_GoBack",true)

        not.scheduleNotificationForDaily(calDate.timeInMillis)

        Log.i("paras", "onReceive:dsfsdg ")
        not.sendAndRequestResponse(contex)


    }
}