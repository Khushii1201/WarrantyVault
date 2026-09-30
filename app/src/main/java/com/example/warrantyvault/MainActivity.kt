package com.example.warrantyvault

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.warrantyvault.databinding.ActivityMainBinding
import com.example.warrantyvault.ui.DashboardFragment
import com.example.warrantyvault.ui.ProductListFragment
import com.example.warrantyvault.worker.WarrantyWorker
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel()
        requestNotificationPermission()
        scheduleWarrantyWorker()

        if (savedInstanceState == null) {
            showFragment(DashboardFragment())
        }

        binding.btnDashboard.setOnClickListener {
            showFragment(DashboardFragment())
        }

        binding.btnProducts.setOnClickListener {
            showFragment(ProductListFragment())
        }

        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this, AddProductActivity::class.java))
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }
    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = android.app.NotificationChannel(
                WarrantyWorker.CHANNEL_ID,
                "Warranty Reminders",
                android.app.NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Reminders for products whose warranties are expiring soon."
            }

            getSystemService(android.app.NotificationManager::class.java)
                .createNotificationChannel(channel)
        }
    }

    private fun scheduleWarrantyWorker() {
        val request = PeriodicWorkRequestBuilder<WarrantyWorker>(
            1, TimeUnit.DAYS
        ).build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "warranty_check",
            ExistingPeriodicWorkPolicy.KEEP,
            request
        )
    }
}
