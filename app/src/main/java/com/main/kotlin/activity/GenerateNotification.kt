package com.main.kotlin.activity

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
import com.main.kotlin.receiver.MyScheduleNotification.Companion.NOTIFICATION_CHANNEL_ID
import com.main.kotlin.receiver.MyScheduleNotification.Companion.default_notification_channel_id
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

import android.widget.*
import java.text.ParseException
import com.android.volley.toolbox.StringRequest

import com.android.volley.RequestQueue

import com.android.volley.Request

import com.android.volley.toolbox.Volley
import android.app.PendingIntent
import com.main.kotlin.receiver.MyScheduleNotification
import com.main.kotlin.R
import com.main.kotlin.receiver.ReceiverDailyNotification


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
    lateinit var btnCancleNotification: Button
    lateinit var btnDailyNotification: Button
    lateinit var radioNotification: RadioButton
    lateinit var radioOddDay: RadioButton
    lateinit var radioNextToNextDay: RadioButton
    var dataFromIntent = false

    var radioSelected = 1


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
        if (intent != null && intent?.getBooleanExtra("Notification_GoBack", false) != null) {
            dataFromIntent = intent?.getBooleanExtra("Notification_GoBack", false)!!
        }
        Log.i("paras", "onCreategoback: ${dataFromIntent}")
        initAllView()

        tvShowAll.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                tvEndDate.text = ""
            }

            override fun afterTextChanged(p0: Editable?) {


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
        btnCancleNotification = findViewById(R.id.CancleNotification)
        btnDailyNotification = findViewById(R.id.btnDailyNotification)
        radioNotification = findViewById(R.id.radio1)
        radioOddDay = findViewById(R.id.radio2)
        radioNextToNextDay = findViewById(R.id.radio3)



        btnSelectTime.setOnClickListener(this)
        btnSelectSecondTime.setOnClickListener(this)
        btnSelectThirdTime.setOnClickListener(this)
        btnSubmit.setOnClickListener(this)
        btnStartDate.setOnClickListener(this)
        btnEndDate.setOnClickListener(this)
        btnCancleNotification.setOnClickListener(this)
        btnDailyNotification.setOnClickListener(this)
        radioOddDay.setOnClickListener(this)
        radioNextToNextDay.setOnClickListener(this)
        radioNotification.setOnClickListener(this)
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
                R.id.CancleNotification -> {
                    val notificationIntent = Intent(this, MyScheduleNotification::class.java)
                    cancelAlarm(this, notificationIntent, 10001)

                    for (i in 20..80) {
                        cancelAlarm(this, notificationIntent, i)
                    }
                }
                R.id.btnDailyNotification -> {
//                    val calendar:Calendar= Calendar.getInstance()
//                    val currentDate=calendar.time
                    val date =
                        SimpleDateFormat("yyyy-MM-dd 00:00:00", Locale.getDefault()).format(Date())
                    val calDate = setAlarmCalender(date)
                    calDate.add(Calendar.DAY_OF_MONTH, 1)
                    Log.i("date", "onClick:   ${calDate.time}")

                    scheduleNotificationForDaily(calDate.timeInMillis)
                }
                R.id.radio1 -> {
                    radioSelected = 1
                }
                R.id.radio2 -> {
                    radioSelected = 2
                }
                R.id.radio3 -> {
                    radioSelected = 3
                }

            }

        }

    }

    fun setAlarmCalender(setDateTime: String): Calendar {
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
        val intent = Intent(this, GenerateNotification::class.java)
        intent.putExtra("Notification_GoBack", true)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        //  pendingIntent.send(this,1,intent)

        val notificationIntent = Intent(applicationContext, GenerateNotification::class.java)
        notificationIntent.putExtra("Notification_GoBack", true)
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingNotificationIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
//        notification.flags = notification.flags or Notification.FLAG_AUTO_CANCEL
//        notification.setLatestEventInfo(
//            applicationContext,
//            notificationTitle,
//            notificationMessage,
//            pendingNotificationIntent
//        )

        val icon = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.nature)
        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(this, default_notification_channel_id)

        builder.setContentTitle("Scheduled Notification -> $dateLong")
        builder.setSmallIcon(R.drawable.ic_bulb)
        builder.setLargeIcon(icon)
        builder.setContentText("parasKansagara")
        builder.setContentIntent(pendingNotificationIntent)
        builder.setPriority(NotificationCompat.PRIORITY_MAX)
        builder.setAutoCancel(true)
        builder.setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
        builder.setChannelId(NOTIFICATION_CHANNEL_ID)
        return builder.build()

    }

    fun scheduleNotification(delay: Long) {
        val longToDate = SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(Date(delay))
        Log.i("paras", "scheduleNotification: $delay   longToDate::$longToDate")
        val notificationIntent = Intent(this, MyScheduleNotification::class.java)
        notificationIntent.putExtra("Notification_GoBack", true)
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

    fun scheduleNotificationForDaily(delay: Long) {
        Log.i("paras", "scheduleNotification: $delay")
        val notificationIntent = Intent(this, ReceiverDailyNotification::class.java)
        notificationIntent.putExtra(ReceiverDailyNotification.NOTIFICATION, getNotification())
        notificationIntent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND)
        notificationIntent.addFlags(Intent.FLAG_RECEIVER_NO_ABORT)
        notificationIntent.addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING)
        notificationIntent.addFlags(Intent.FLAG_FROM_BACKGROUND)

        val pendingIntent = PendingIntent.getBroadcast(
            this,
            10001,
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

        if (tvShowAll.text.toString() == "") {

            Toast.makeText(this, "Please select Start Date...", Toast.LENGTH_SHORT).show()
        } else if (tvEndDate.text.toString() == "") {

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
        view.text = sdf.format(calendar.time) + ":00"
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
        for (i in 0..listOfDate.size - 1) {
            for (j in 0..listOfTime.size - 1) {

                val dateTime = listOfDate[i] + " " + listOfTime[j]
                val dateTimeLong = setAlarmCalender(dateTime).timeInMillis

                Log.i("paras", "createNotification: $dateTime  -- ${dateTimeLong}")

                when {
                    i % radioSelected == 0 -> {
                        scheduleNotification(dateTimeLong)

                    }
                }
            }

        }
    }

    fun getAllTime(): ArrayList<String> {
        val list = ArrayList<String>()

        if (tvFirstTime.text.toString() != "") {

            list.add(tvFirstTime.text.toString())
        }
        if (tvSecondTime.text.toString() != "") {
            list.add(tvSecondTime.text.toString())
        }
        if (tvThirdTime.text.toString() != "") {
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

    override fun onBackPressed() {
        super.onBackPressed()
        if (dataFromIntent) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            finish()
        }

    }

}