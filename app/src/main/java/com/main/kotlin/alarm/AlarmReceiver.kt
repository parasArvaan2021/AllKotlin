package com.main.kotlin.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.main.kotlin.alarm.NotificationUtil.createNotification
import org.json.JSONException
import org.json.JSONObject

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val reminderList: JSONObject
        val mainJson: JSONObject
        Log.i("TAG", "AlarmReceiver: " + resultData)
        try {
            reminderList = JSONObject(intent.getStringExtra("NOTIFICATION_ID"))
            mainJson = JSONObject(intent.getStringExtra("mainJson"))
            createNotification(context, reminderList, mainJson)

            // Update lists in tab fragments
            updateLists(context)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    fun updateLists(context: Context?) {
        val intent = Intent("BROADCAST_REFRESH")
        LocalBroadcastManager.getInstance(context!!).sendBroadcast(intent)
    }
}