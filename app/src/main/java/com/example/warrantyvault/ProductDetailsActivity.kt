package com.example.warrantyvault

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.warrantyvault.data.AppDatabase
import com.example.warrantyvault.databinding.ActivityProductDetailsBinding
import com.example.warrantyvault.util.DateUtils
import kotlinx.coroutines.launch

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra(EXTRA_PRODUCT_ID, -1)
        if (id == -1) {
            finish()
            return
        }

        lifecycleScope.launch {
            val product = AppDatabase.getInstance(this@ProductDetailsActivity)
                .productDao()
                .getById(id)

            if (product == null) {
                finish()
                return@launch
            }

            binding.tvDetailsName.text = product.productName

            val days = DateUtils.daysRemaining(product.expiryDate)
            val status = when {
                days < 0 -> "EXPIRED"
                days == 0L -> "EXPIRES TODAY"
                else -> "ACTIVE"
            }

            binding.tvDetailsInfo.text = """
                Brand: ${product.brand}
                Price: ₹${"%.2f".format(product.price)}

                Purchase Date: ${DateUtils.format(product.purchaseDate)}
                Warranty: ${product.warrantyMonths} months
                Expiry Date: ${DateUtils.format(product.expiryDate)}

                Status: $status
                Remaining: ${if (days < 0) "Expired ${-days} days ago" else "$days days"}
            """.trimIndent()

            binding.btnViewInvoice.isEnabled = product.invoicePath != null

            binding.btnViewInvoice.setOnClickListener {
                product.invoicePath?.let { path ->
                    startActivity(
                        Intent(this@ProductDetailsActivity, InvoiceActivity::class.java)
                            .putExtra(InvoiceActivity.EXTRA_PATH, path)
                    )
                }
            }
        }
    }

    companion object {
        const val EXTRA_PRODUCT_ID = "product_id"
    }
}
