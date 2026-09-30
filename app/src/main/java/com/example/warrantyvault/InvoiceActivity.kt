package com.example.warrantyvault

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.warrantyvault.databinding.ActivityInvoiceBinding
import java.io.File

class InvoiceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInvoiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInvoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val path = intent.getStringExtra(EXTRA_PATH)

        if (path.isNullOrEmpty()) {
            binding.imageInvoice.visibility = View.GONE
            binding.tvNoInvoice.visibility = View.VISIBLE
            return
        }

        val file = File(path)
        if (!file.exists()) {
            binding.imageInvoice.visibility = View.GONE
            binding.tvNoInvoice.visibility = View.VISIBLE
            return
        }

        val bitmap = BitmapFactory.decodeFile(file.absolutePath)

        if (bitmap == null) {
            binding.imageInvoice.visibility = View.GONE
            binding.tvNoInvoice.visibility = View.VISIBLE
        } else {
            binding.imageInvoice.setImageBitmap(bitmap)
        }
    }

    companion object {
        const val EXTRA_PATH = "invoice_path"
    }
}
