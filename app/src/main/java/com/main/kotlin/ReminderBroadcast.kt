package com.main.kotlin

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

class ReminderBroadcast : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val _notificationUtils = NotificationUtils(context)
        val _builder: NotificationCompat.Builder =
            _notificationUtils.setNotification("Testing", "Testing notification system")
        _notificationUtils.manager!!.notify(101, _builder.build())
    }
}