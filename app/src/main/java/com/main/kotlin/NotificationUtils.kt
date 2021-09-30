package com.main.kotlin

import android.app.*
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationUtils(base: Context) : ContextWrapper(base) {
    private var _notificationManager: NotificationManager? = null
    private val _context: Context

    val CHANNEL_ID = "channel_id"
    val NOTIFICATION_ID = 1

    fun setNotification(title: String?, body: String?): NotificationCompat.Builder {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.copy_text)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "NOTIFICATION_CHANNEL_NAME",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            manager!!.createNotificationChannel(channel)
        }
    }

    val manager: NotificationManager?
        get() {
            if (_notificationManager == null) {
                _notificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            }
            return _notificationManager
        }

    fun setReminder(timeInMillis: Long) {
        val _intent = Intent(_context, ReminderBroadcast::class.java)
        val _pendingIntent = PendingIntent.getBroadcast(_context, 0, _intent, 0)
        val _alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        _alarmManager[AlarmManager.RTC_WAKEUP, timeInMillis] = _pendingIntent
    }

    init {
        _context = base
        createChannel()
    }
}