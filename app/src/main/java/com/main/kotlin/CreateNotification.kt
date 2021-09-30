package com.main.kotlin

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class CreateNotification(val context: Context, val workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {

        NotificationManager(context).createNotification(
            inputData.getString("title").toString(),
            inputData.getString("message").toString(),
            inputData.getLong("long", 100)

        )
        return Result.success()
    }

}