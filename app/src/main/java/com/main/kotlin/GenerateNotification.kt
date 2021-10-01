package com.main.kotlin

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.main.kotlin.MyScheduleNotification.Companion.NOTIFICATION_CHANNEL_ID
import com.main.kotlin.MyScheduleNotification.Companion.default_notification_channel_id
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

import android.widget.*
import java.text.ParseException
import com.android.volley.toolbox.StringRequest

import com.android.volley.RequestQueue

import com.android.volley.Request

import com.android.volley.toolbox.Volley

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
    lateinit var btnSubmit: Button

    var dateLong: Long = 0L


    var calendarStartDate = Calendar.getInstance()
    var calendarFirstTime = Calendar.getInstance()
    var calendarEndDate = Calendar.getInstance()

    private var mRequestQueue: RequestQueue? = null
    private var mStringRequest: StringRequest? = null
    private val url = "http://www.mocky.io/v2/597c41390f0000d002f4dbd1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_notification)
        initAllView()

        tvShowAll.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {

                if (!tvEndDate.text.isEmpty()) {
                    tvEndDate.text = ""
                }
            }

        })
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
        btnSubmit = findViewById(R.id.btnSubmit)

        btnSelectTime.setOnClickListener(this)
        btnSelectSecondTime.setOnClickListener(this)
        btnSelectThirdTime.setOnClickListener(this)
        btnSubmit.setOnClickListener(this)
        btnStartDate.setOnClickListener(this)
        btnEndDate.setOnClickListener(this)

    }


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
                updateLabel(calendarStartDate, tvShowAll)

            }, Year,
            month,
            dayOfMonth
        )

        datePickerDialog.show()
    }

    fun selectEndDate() {

        val year = calendarEndDate.get(Calendar.YEAR)
        val month = calendarEndDate.get(Calendar.MONTH)
        val dayOfMonth = calendarEndDate.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { datePicker, year, month, day ->
                calendarEndDate[Calendar.YEAR] = year
                calendarEndDate[Calendar.MONTH] = month
                calendarEndDate[Calendar.DAY_OF_MONTH] = day
                updateLabel(calendarEndDate, tvEndDate)

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
                updateTime(calendarFirstTime, tvFirstTime)

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
                updateTime(calendar, tvSecondTime)
            }, hour, minute, false
        ).show()


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
                updateTime(calendar, tvThirdTime)

            }, hour, minute, false
        ).show()
    }

    fun getDatesBetween(dateString1: String, dateString2: String): ArrayList<String> {
        val dates = ArrayList<String>()
        val input = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        var date1: Date? = null
        var date2: Date? = null
        try {
            date1 = input.parse(dateString1)
            date2 = input.parse(dateString2)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val cal1 = Calendar.getInstance()
        cal1.time = date1
        val cal2 = Calendar.getInstance()
        cal2.time = date2
        while (!cal1.after(cal2)) {
            val output = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            dates.add(output.format(cal1.time))
            cal1.add(Calendar.DATE, 1)
        }
        return dates
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClick(v: View?) {

        if (v != null) {
            when (v.id) {
                R.id.btnSelectFirstTime -> {

                    selectTime()

                }
                R.id.btnSelectSecondTime -> {

                    selectSecondTime()
                }
                R.id.btnSelectThirdTime -> {

                    selectThirdTime()

                }
                R.id.btnSubmit -> {

                    submit(tvShowAll.text.toString(), tvEndDate.text.toString())

                }
                R.id.btnStartDate -> {
                    selectStartDate()
                }
                R.id.btnEndDate -> {
                    selectEndDate()
                }

            }

        }

    }

    private fun setAlarmCalender(setDateTime: String): Calendar {
        Log.i("Date and Time", "setAlarmCalender: $setDateTime")
        val mCalendar1: Calendar = Calendar.getInstance()
        try {
            mCalendar1.time = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(setDateTime)
            return mCalendar1
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return mCalendar1
    }

    private fun getNotification(): Notification {
        val intent = Intent(this, GenerateNotification::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val icon = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.nature)
        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(this, default_notification_channel_id)
        builder.setContentTitle("Scheduled Notification -> $dateLong")
        builder.setSmallIcon(R.drawable.ic_bulb)
        builder.setLargeIcon(icon)
        builder.setContentText("parasKansagara")
        builder.setContentIntent(pendingIntent)
        builder.setPriority(NotificationCompat.PRIORITY_MAX)
        builder.setAutoCancel(true)
        builder.setChannelId(NOTIFICATION_CHANNEL_ID)
        return builder.build()

    }

    fun scheduleNotification(delay: Long) {
        Log.i("paras", "scheduleNotification: $delay")
        val notificationIntent = Intent(this, MyScheduleNotification::class.java)
        notificationIntent.putExtra(MyScheduleNotification.NOTIFICATION_ID, delay.toString())
        notificationIntent.putExtra(MyScheduleNotification.NOTIFICATION, getNotification())
        notificationIntent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND)
        notificationIntent.addFlags(Intent.FLAG_RECEIVER_NO_ABORT)
        notificationIntent.addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING)
        notificationIntent.addFlags(Intent.FLAG_FROM_BACKGROUND)
        val min = 20
        val max = 80
        val random: Int = Random().nextInt(max - min + 1) + min

        val pendingIntent = PendingIntent.getBroadcast(
            this,
            random,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = (getSystemService(Context.ALARM_SERVICE) as AlarmManager)
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, delay, pendingIntent)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun submit(first: String, second: String) {

        var listOfDate = ArrayList<String>()
        var listOfTimeSelect = ArrayList<String>()

        if (tvShowAll.text.isEmpty()) {
            Toast.makeText(this, "Please select Start Date...", Toast.LENGTH_SHORT).show()
        } else if (tvEndDate.text.isEmpty()) {
            Toast.makeText(this, "Please Select End Date", Toast.LENGTH_SHORT).show()
        } else {
            listOfDate = getDatesBetween(first, second)
            listOfTimeSelect = getAllTime()
            Log.i("paras", "submit: ifelse")
            clearAllTextView()
        }

        Log.i("paras", "submit:$listOfDate ")


        Log.i("paras", "submitTime: $listOfTimeSelect")

        createNotification(listOfDate, listOfTimeSelect)

    }


    private fun updateLabel(calendar: Calendar, view: TextView) {
        val myFormat = "yyyy-MM-dd" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        view?.text = sdf.format(calendar.time)

    }

    private fun updateTime(calendar: Calendar, view: TextView) {
        val format = "HH:mm"
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        view.text = sdf.format(calendar.time)+":00"
    }

    private fun clearAllTextView() {
        tvEndDate.text = ""
        tvShowAll.text = ""
        tvFirstTime.text = ""
        tvSecondTime.text = ""
        tvThirdTime.text = ""

    }

    private fun createNotification(
        listOfDate: ArrayList<String>,
        listOfTime: ArrayList<String>
    ) {
        for (i in 0 until listOfDate.size) {
            for (j in 0 until listOfTime.size) {

                val dateTime = listOfDate[i] + " " + listOfTime[j]
                val dateTimeLong = setAlarmCalender(dateTime).timeInMillis

                Log.i("paras", "createNotification: $dateTime  -- ${dateTimeLong}")

                scheduleNotification(dateTimeLong)
            }
        }

    }

    private fun getAllTime(): ArrayList<String> {
        val list = ArrayList<String>()

        if (!tvFirstTime.text.isEmpty()) {
            list.add(tvFirstTime.text.toString())
        }
        if (!tvSecondTime.text.isEmpty()) {
            list.add(tvSecondTime.text.toString())
        }
        if (!tvThirdTime.text.isEmpty()) {
            list.add(tvThirdTime.text.toString())
        }

        return list
    }
    fun sendAndRequestResponse(context: Context?) {

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(context)

        mStringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                Log.i("Response", "$response")
            }
        ) { error -> Log.i("TAG", "Error :$error") }
        mRequestQueue!!.add(mStringRequest)
    }
}