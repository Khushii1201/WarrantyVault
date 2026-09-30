package com.example.warrantyvault.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.warrantyvault.data.Product
import com.example.warrantyvault.databinding.ItemProductBinding
import com.example.warrantyvault.util.DateUtils

class ProductAdapter(
    private val products: List<Product>,
    private val onClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(
        private val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.tvProductName.text = product.productName
            binding.tvBrandPrice.text =
                "${product.brand}  •  ₹${"%.2f".format(product.price)}"

            val days = DateUtils.daysRemaining(product.expiryDate)

            binding.tvWarranty.text = when {
                days < 0 -> "Warranty expired"
                days == 0L -> "Warranty expires today"
                days <= 30 -> "Warranty: $days days remaining"
                else -> "Warranty expires: ${DateUtils.format(product.expiryDate)}"
            }

            binding.root.setOnClickListener {
                onClick(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int = products.size
}
