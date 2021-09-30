package com.main.kotlin

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.*


class GenerateNotification : AppCompatActivity() {

    lateinit var btnStartDate: Button
    lateinit var btnEndDate: Button
    lateinit var btnSelectTime: Button
    lateinit var btnSelectSecondTime: Button
    lateinit var btnSelectThirdTime: Button
    lateinit var tvShowAll: TextView

    var calendarStartDate = Calendar.getInstance()
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
        tvShowAll = findViewById(R.id.tvShowAll)
    }


    fun onClickStartDate(view: View) {
        selectStartDate()
    }

    fun onClickEndDate(view: View) {
        selectEndDate()
    }
    fun clickFirstTime(view: View) {
        selectTime()
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

                tvShowAll.text = day.toString() + "/" + (month + 1) + "/" + year
            },
            year,
            month,
            dayOfMonth
        )
        datePickerDialog.datePicker.minDate = calendarStartDate.timeInMillis
        datePickerDialog.show()

    }

    fun selectTime() {
        val calendar=Calendar.getInstance()
        val hour=calendar.get(Calendar.HOUR_OF_DAY)
        val minute=calendar.get(Calendar.MINUTE)
        val timePicker = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener
        { timePicker, hour, minute ->
                calendar[Calendar.HOUR_OF_DAY]=hour
                calendar[Calendar.MINUTE]=minute
            tvShowAll.text=hour.toString()+ ":" + minute

        }, hour, minute, false).show()
    }

}