package com.main.kotlin

import android.R.attr
import android.app.DatePickerDialog.OnDateSetListener

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import androidx.annotation.RequiresApi
import androidx.work.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

import android.content.Context

import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import android.R.attr.delay
import android.app.*
import android.app.Notification
import android.graphics.BitmapFactory

import android.os.SystemClock
import android.widget.*
import java.text.DateFormat
import java.text.ParseException


class Notification : AppCompatActivity() {

    val myCalendar: Calendar = Calendar.getInstance()
    lateinit var tvShowDate: TextView
    var dateLong: Long = 0L

    companion object {
        val NOTIFICATION_CHANNEL_ID = "10001";
        val default_notification_channel_id = "default";
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        val btnSendNotification: Button = findViewById(R.id.SendNotification)
        val btnStartDate: Button = findViewById(R.id.SechduleNotification)
        tvShowDate = findViewById(R.id.tvShowDate)
        val btnCancle: Button = findViewById(R.id.btnCancle)
        val btnNotify: Button = findViewById(R.id.btnNotify)

        btnSendNotification.setOnClickListener(View.OnClickListener {

            createNotification()
        })
        btnStartDate.setOnClickListener(View.OnClickListener {
            selectDate()
        })
    }

    private fun createNotification() {
        val request: WorkRequest = OneTimeWorkRequestBuilder<CreateNotification>()
            .setInitialDelay(10, TimeUnit.SECONDS)
            .setInputData(
                workDataOf(
                    "title" to "Todo Created",
                    "message" to "A new Todo has been Created"
                )
            )
            .build()

        WorkManager.getInstance(this).enqueue(request)

    }

    private fun selectDate() {
        DatePickerDialog(
            this@Notification, date,
            myCalendar.get(Calendar.YEAR),
            myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)

        ).show()
//        TimePickerDialog(this, time)
        Log.i("paras", "selectDate: ")
    }


    private fun updateLabel() {
        val myFormat = "dd-MM-yyyy" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        val date1 = myCalendar.time
        tvShowDate?.text = sdf.format(date1)

        dateLong = setAlarmCalender("${tvShowDate?.text.toString()} 17:48:50").timeInMillis
        Log.i("paras", "updateLabel: $dateLong")

        scheduleNotification(
            getNotification("parasKansagara"),
            dateLong
        )
    }

    private fun getNotification(content: String): android.app.Notification {
        val icon = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.nature)
        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(this, default_notification_channel_id)
        builder.setContentTitle("Scheduled Notification -> $dateLong")
        builder.setSmallIcon(R.drawable.ic_bulb)
        builder.setLargeIcon(icon)
        builder.setContentText(content)
        builder.setAutoCancel(true)
        builder.setChannelId(NOTIFICATION_CHANNEL_ID)
        return builder.build()

    }

    private fun scheduleNotification(notification: Notification, delay: Long) {
        Log.i("paras", "scheduleNotification: $delay")
        val notificationIntent = Intent(this, MyScheduleNotification::class.java)
        notificationIntent.putExtra(MyScheduleNotification.NOTIFICATION_ID, 1)
        notificationIntent.putExtra(MyScheduleNotification.NOTIFICATION, notification)
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )


        val alarmManager = (getSystemService(Context.ALARM_SERVICE) as AlarmManager)
        alarmManager[AlarmManager.RTC_WAKEUP, delay] = pendingIntent
    }

    var date =
        OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            myCalendar[Calendar.YEAR] = year
            myCalendar[Calendar.MONTH] = monthOfYear
            myCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
            Log.i("paras", "date: ")
            updateLabel()
        }

    val time = TimePickerDialog.OnTimeSetListener { view, hour, min ->
        myCalendar[Calendar.HOUR_OF_DAY] = hour
        myCalendar[Calendar.MINUTE] = min
    }

    private fun setAlarmCalender(setDateTime: String): Calendar {
        Log.i("Date and Time", "setAlarmCalender: $setDateTime")
        val mCalendar1: Calendar = Calendar.getInstance()
        try {
            mCalendar1.time = SimpleDateFormat("dd-MM-yyyy hh:mm:ss").parse(setDateTime)
            return mCalendar1
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return mCalendar1
    }
}


