package com.example.warrantyvault.worker

import android.Manifest
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.warrantyvault.R
import com.example.warrantyvault.data.AppDatabase
import com.example.warrantyvault.util.DateUtils

class WarrantyWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        if (android.os.Build.VERSION.SDK_INT >= 33 &&
            ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return Result.success()
        }

        val products = AppDatabase.getInstance(applicationContext)
            .productDao()
            .getAll()

        val manager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        products.forEach { product ->
            val days = DateUtils.daysRemaining(product.expiryDate)

            if (days in 0..7) {
                val message = if (days == 0L) {
                    "${product.productName} warranty expires today."
                } else {
                    "${product.productName} warranty expires in $days days."
                }

                val notification = NotificationCompat.Builder(
                    applicationContext,
                    CHANNEL_ID
                )
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setContentTitle("Warranty Reminder")
                    .setContentText(message)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true)
                    .build()

                manager.notify(product.id, notification)
            }
        }

        return Result.success()
    }

    companion object {
        const val CHANNEL_ID = "warranty_reminders"
    }
}
