package com.main.kotlin

import android.annotation.SuppressLint
import android.app.*
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import cn.qqtheme.framework.picker.TimePicker
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class TestingNotification : AppCompatActivity(), View.OnClickListener {

    lateinit var mCalendar: Calendar

    lateinit var etAddDailyScheduleDate: EditText
    lateinit var etAddDailyScheduleTime: EditText
    lateinit var tvAddDailyScheduleSubmit: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testing_notification)

        etAddDailyScheduleDate = findViewById(R.id.etAddDailyScheduleDate) as EditText
        etAddDailyScheduleTime = findViewById(R.id.etAddDailyScheduleTime) as EditText
        tvAddDailyScheduleSubmit = findViewById(R.id.tvAddDailyScheduleSubmit) as TextView

        etAddDailyScheduleDate.setText(dateFormat(System.currentTimeMillis()))
        etAddDailyScheduleTime.setText(timeFormat(System.currentTimeMillis()))

        mCalendar = Calendar.getInstance()
        etAddDailyScheduleDate.setOnClickListener(this)
        etAddDailyScheduleTime.setOnClickListener(this)
        tvAddDailyScheduleSubmit.setOnClickListener(this)
    }

    fun datePicker() {
        val DatePicker = DatePickerDialog(
            this,
            { DatePicker, year, month, dayOfMonth ->
                mCalendar = Calendar.getInstance()
                mCalendar.set(Calendar.YEAR, year)
                mCalendar.set(Calendar.MONTH, month)
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                etAddDailyScheduleDate.setText(dateFormat(mCalendar.getTime().time))
                etAddDailyScheduleTime.setText(timeFormat(System.currentTimeMillis()))
            }, mCalendar[Calendar.YEAR], mCalendar[Calendar.MONTH],
            mCalendar[Calendar.DAY_OF_MONTH]
        )
        DatePicker.show()
        DatePicker.setCancelable(false)
        DatePicker.datePicker.minDate = System.currentTimeMillis()
    }

    fun createUpdateSchedule() {
        try {
            val longDate = setAlarmCalender(etAddDailyScheduleDate.text.toString() + " " + etAddDailyScheduleTime.text.toString() + ":00").timeInMillis
//            sendNotification("Hello", longDate)
            reminderNotification(longDate)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    fun dateFormat(date: Long): String? {
        return SimpleDateFormat("dd-MM-yyyy").format(date)
    }

    fun timeFormat(time: Long): String? {
        return SimpleDateFormat("HH:mm:ss").format(time)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.etAddDailyScheduleDate -> datePicker()
            R.id.tvAddDailyScheduleSubmit -> {
                createUpdateSchedule()
            }
            R.id.etAddDailyScheduleTime -> TimePickers(
                this,
                etAddDailyScheduleDate.getText().toString(),
                etAddDailyScheduleTime
            )
        }
    }

    private fun setAlarmCalender(setDateTime: String): Calendar {
        val mCalendar1 = Calendar.getInstance()

        try {
            mCalendar1.time = SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(setDateTime)
            Log.i("TAG", "setAlarmCalender: Long Time1 :- ${mCalendar1.timeInMillis}")

            return mCalendar1
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        Log.i("TAG", "setAlarmCalender: Long Time2 :- ${mCalendar1.timeInMillis}")

        return mCalendar1
    }

    fun TimePickers(activity: Activity, date: String?, setTime: EditText) {

        val picker =
            TimePicker(
                activity,
                TimePicker.HOUR_OF_DAY
            )
        picker.setLabel("", "")
        picker.setTitleText("Select Time")
        picker.setTitleTextColor(activity.resources.getColor(R.color.white))
        picker.setTopBackgroundColor(activity.resources.getColor(R.color.colorPrimary))
        picker.setTopLineColor(activity.resources.getColor(R.color.colorPrimary))
        picker.setCancelTextColor(activity.resources.getColor(R.color.white))
        picker.setSubmitTextColor(activity.resources.getColor(R.color.white))
        picker.setTopLineVisible(false)
        picker.setOnTimePickListener { selectedHour, selectedMinute ->
            Log.i("TAG", "TimePickers: $selectedHour:$selectedMinute")
            val time = "$selectedHour:$selectedMinute:00"
            if (dateFormat(
                    System.currentTimeMillis()
                )?.let {
                    date?.let { it1 ->
                        dateEqualCompare(
                            it1, it
                        )
                    }
                } == true
            ) if (timeFormat(
                    System.currentTimeMillis()
                )?.let {
                    timeCompare(
                        time, it
                    )
                } == true
            ) setTime.setText(time) else Toast.makeText(
                activity,
                "Time has to be less than Current Time",
                Toast.LENGTH_SHORT
            ).show() else setTime.setText(time)
        }
        picker.show()
    }

    private fun timeCompare(fromCompare: String, toCompare: String): Boolean {
        var selectTime: Long = 0
        var systemTime: Long = 0
        try {
            selectTime = SimpleDateFormat("HH:mm:ss").parse(fromCompare).time
            systemTime = SimpleDateFormat("HH:mm:ss").parse(toCompare).time
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return selectTime >= systemTime
    }

    private fun dateEqualCompare(fromCompare: String, toCompare: String): Boolean {
        val selectDate: Long =
            convertStringToDate(fromCompare)!!.time
        val systemDate: Long =
            convertStringToDate(toCompare)!!.time
        return selectDate == systemDate
    }

    fun convertStringToDate(date: String?): Date? {
        try {
            return SimpleDateFormat("dd-MM-yyyy").parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return Date()
    }

    private fun sendNotification(messageBody: String, timeInMillis: Long) {

        Log.i(
            "TAG",
            "sendNotification: ${SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(Date(timeInMillis))}"
        )

        val random = Random()
        val Low = 10
        val High = 100
        val notificationId = random.nextInt(High - Low) + Low
        val icon = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher_round)
        val intent = Intent(this, TestingNotification::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        val pendingIntent = PendingIntent.getActivity(
            this,
            (Math.random() * 100).toInt(), intent, PendingIntent.FLAG_ONE_SHOT
        )
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, "M_CH_ID")
            .setColor(NotificationCompat.COLOR_DEFAULT)
            .setSmallIcon(getNotificationIcon())
            .setContentTitle("Embie")
            .setWhen(timeInMillis)
            .setShowWhen(true)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setLargeIcon(icon)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            @SuppressLint("WrongConstant") val notificationChannel =
                NotificationChannel("2", "ART APP NOTIFICATION", importance)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.vibrationPattern =
                longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            assert(notificationManager != null)
            notificationBuilder.setChannelId("2")
            notificationManager.createNotificationChannel(notificationChannel)
        }
        notificationManager.notify(
            101,
            notificationBuilder.build()
        )
    }

    private fun getNotificationIcon(): Int {
        val useWhiteIcon = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
        return if (useWhiteIcon) R.mipmap.ic_launcher_round else R.mipmap.ic_launcher_round
    }

    fun reminderNotification(timeInMillis: Long) {
        val _notificationUtils = NotificationUtils(this)
        val _currentTime = timeInMillis
        val _triggerReminder = _currentTime //triggers a reminder after 10 seconds.
        _notificationUtils.setReminder(_triggerReminder)
    }
}