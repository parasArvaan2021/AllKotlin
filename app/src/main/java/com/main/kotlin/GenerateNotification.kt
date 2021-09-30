package com.main.kotlin

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.app.NotificationCompat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class GenerateNotification : AppCompatActivity(), View.OnClickListener {

    lateinit var btnStartDate: Button
    lateinit var btnEndDate: Button
    lateinit var btnSelectTime: Button
    lateinit var btnSelectSecondTime: Button
    lateinit var btnSelectThirdTime: Button
    lateinit var tvShowAll: TextView
    lateinit var tvEndDate: TextView
    lateinit var tvFirstTime: TextView
    lateinit var tvSecondTime: TextView
    lateinit var tvThirdTime: TextView
    var howManyCreateNotification = 0
    var dateLong: Long = 0L

    var calendarStartDate = Calendar.getInstance()
    var calendarFirstTime = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_notification)
        initAllView()
    }

    fun initAllView() {
        btnStartDate = findViewById(R.id.btnStartDate)
        btnEndDate = findViewById(R.id.btnEndDate)
        btnSelectTime = findViewById(R.id.btnSelectFirstTime)
        btnSelectSecondTime = findViewById(R.id.btnSelectSecondTime)
        btnSelectThirdTime = findViewById(R.id.btnSelectThirdTime)
        tvShowAll = findViewById(R.id.tvShowStartDate)
        tvEndDate = findViewById(R.id.tvShowEndDate)
        tvFirstTime = findViewById(R.id.tvFirstTime)
        tvSecondTime = findViewById(R.id.tvSecondTime)
        tvThirdTime = findViewById(R.id.tvThirdTime)

        btnSelectTime.setOnClickListener(this)
        btnSelectSecondTime.setOnClickListener(this)
        btnSelectThirdTime.setOnClickListener(this)
    }


    fun onClickStartDate(view: View) {
        selectStartDate()
    }

    fun onClickEndDate(view: View) {
        selectEndDate()
    }

    //fun clickFirstTime(view: View) {
    //    selectTime()
    // }

    //  fun clickSecondTime(view: View) {
    //     selectSecondTime()
    //  }

    // fun clickThirdTime(view: View) {
    //    selectThirdTime()
    //  }

    private fun selectStartDate() {

        val Year = calendarStartDate.get(Calendar.YEAR)
        val month = calendarStartDate.get(Calendar.MONTH)
        val dayOfMonth = calendarStartDate.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(
            this,
            { datePicker, year, month, day ->
                calendarStartDate[Calendar.YEAR] = year
                calendarStartDate[Calendar.MONTH] = month
                calendarStartDate[Calendar.DAY_OF_MONTH] = day
                tvShowAll.text = day.toString() + "/" + (month + 1) + "/" + year
            }, Year,
            month,
            dayOfMonth
        )

        datePickerDialog.show()
    }

    fun selectEndDate() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { datePicker, year, month, day ->
                calendar[Calendar.YEAR] = year
                calendar[Calendar.MONTH] = month
                calendar[Calendar.DAY_OF_MONTH] = dayOfMonth

                tvEndDate.text = day.toString() + "-" + (month + 1) + "-" + year
            },
            year,
            month,
            dayOfMonth
        )
        datePickerDialog.datePicker.minDate = calendarStartDate.timeInMillis
        datePickerDialog.show()

    }

    fun selectTime() {
        val hour = calendarFirstTime.get(Calendar.HOUR_OF_DAY)
        val minute = calendarFirstTime.get(Calendar.MINUTE)
        val timePicker = TimePickerDialog(
            this, TimePickerDialog.OnTimeSetListener
            { timePicker, hour, minute ->
                calendarFirstTime[Calendar.HOUR_OF_DAY] = hour
                calendarFirstTime[Calendar.MINUTE] = minute
                tvFirstTime.text = hour.toString() + ":" + minute

            }, hour, minute, false
        ).show()
    }

    private fun selectSecondTime() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val timePicker = TimePickerDialog(
            this, TimePickerDialog.OnTimeSetListener
            { timePicker, hour, minute ->
                calendar[Calendar.HOUR_OF_DAY] = hour
                calendar[Calendar.MINUTE] = minute
                tvSecondTime.text = hour.toString() + ":" + minute

            }, hour, minute, false
        )

            .show()
    }

    private fun selectThirdTime() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val timePicker = TimePickerDialog(
            this, TimePickerDialog.OnTimeSetListener
            { timePicker, hour, minute ->
                calendar[Calendar.HOUR_OF_DAY] = hour
                calendar[Calendar.MINUTE] = minute
                tvThirdTime.text = hour.toString() + ":" + minute

            }, hour, minute, false
        ).show()
    }

    fun createNotification() {
        if (howManyCreateNotification == 1) {
            val concateString = "${tvShowAll.text} ${tvFirstTime.text}:00"

            dateLong = setAlarmCalender(concateString).timeInMillis

            Log.i("paras", "createNotification: $dateLong        $concateString")
        }else if (howManyCreateNotification==2){
            val concateString = "${tvShowAll.text} ${tvSecondTime.text}:00"

            dateLong = setAlarmCalender(concateString).timeInMillis
            Log.i("paras", "createNotification: $dateLong        $concateString")
        }else if (howManyCreateNotification==3){

            val concateString = "${tvShowAll.text} ${tvThirdTime.text}:00"

            dateLong = setAlarmCalender(concateString).timeInMillis
            Log.i("paras", "createNotification: $dateLong        $concateString")
        }


    }

    override fun onClick(v: View?) {

        if (v != null) {
            when (v.id) {
                R.id.btnSelectFirstTime -> {
                    howManyCreateNotification = 1
                    selectTime()
                    createNotification()
                }
                R.id.btnSelectSecondTime -> {
                    howManyCreateNotification = 2
                    selectSecondTime()
                    createNotification()
                }
                R.id.btnSelectThirdTime -> {
                    howManyCreateNotification = 3
                    selectThirdTime()
                    createNotification()
                }

            }
            Log.i("paras", "onClick: $howManyCreateNotification")
        }

    }

    private fun setAlarmCalender(setDateTime: String): Calendar {
        Log.i("Date and Time", "setAlarmCalender: $setDateTime")
        val mCalendar1: Calendar = Calendar.getInstance()
        try {
            mCalendar1.time = SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(setDateTime)
            return mCalendar1
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return mCalendar1
    }

    private fun getNotification(): Notification {
        val icon = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.nature)
        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(this, NotificationClass.default_notification_channel_id)
        builder.setContentTitle("Scheduled Notification -> $dateLong")
        builder.setSmallIcon(R.drawable.ic_bulb)
        builder.setLargeIcon(icon)
        builder.setContentText("parasKansagara")
        builder.setAutoCancel(true)
        builder.setChannelId(NotificationClass.NOTIFICATION_CHANNEL_ID)
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


}