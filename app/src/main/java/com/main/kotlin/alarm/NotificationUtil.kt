package com.main.kotlin.alarm

import android.app.Notification
import android.app.NotificationManager
import org.json.JSONObject
import android.content.Intent
import com.main.kotlin.TestingNotification
import android.app.PendingIntent
import android.content.Context
import com.main.kotlin.R
import android.content.SharedPreferences
import android.graphics.Color
import android.net.Uri
import android.preference.PreferenceManager
import androidx.core.app.NotificationCompat

object NotificationUtil {
    @JvmStatic
    fun createNotification(context: Context, reminder: JSONObject, mainJson: JSONObject) {
        val viewIntent = Intent(context, TestingNotification::class.java).putExtra(
            "onlyShow",
            mainJson.toString()
        )
        val pending = PendingIntent.getActivity(
            context,
            1001,
            viewIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val builder = NotificationCompat.Builder(context)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setStyle(NotificationCompat.BigTextStyle().bigText(reminder.optString("desription")))
            .setContentTitle(reminder.optString("title"))
            .setContentText(reminder.optString("description"))
            .setSubText(context.resources.getString(R.string.app_name))
            .setTicker(reminder.optString("title"))
            .setContentIntent(pending)
            .setAutoCancel(true)
            .setLights(Color.BLUE, 700, 1500)
            .setOngoing(true)
            .setVibrate(longArrayOf(0, 300, 0))
            .setPriority(Notification.PRIORITY_HIGH)

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1001, builder.build())
    }

    fun cancelNotification(context: Context, notificationId: Int) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(notificationId)
    }
}