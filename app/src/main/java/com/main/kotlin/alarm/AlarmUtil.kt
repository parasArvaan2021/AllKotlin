package com.main.kotlin.alarm

import android.content.Intent
import org.json.JSONObject
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.util.Log
import java.util.*

object AlarmUtil {
    fun setAlarm(
        context: Context,
        intent: Intent,
        notificationId: Int,
        reminderObj: JSONObject,
        mainJson: JSONObject,
        calendar: Calendar
    ) {
        Log.i("TAG", "setAlarm: " + calendar.timeInMillis)
        intent.putExtra("NOTIFICATION_ID", reminderObj.toString())
        intent.putExtra("mainJson", mainJson.toString())
        //        intent.putExtra
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            notificationId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    }

    fun cancelAlarm(context: Context, intent: Intent?, notificationId: Int) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            notificationId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        alarmManager.cancel(pendingIntent)
    } //    Intent alarmIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
    //    AlarmUtil.setAlarm(getApplicationContext(), alarmIntent, reminder.getId(), mCalendar);
}