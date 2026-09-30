package com.example.warrantyvault.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.warrantyvault.ProductDetailsActivity
import com.example.warrantyvault.R
import com.example.warrantyvault.data.AppDatabase
import com.example.warrantyvault.databinding.FragmentProductListBinding
import kotlinx.coroutines.launch

class ProductListFragment : Fragment(R.layout.fragment_product_list) {

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentProductListBinding.bind(view)
        binding.recyclerProducts.layoutManager = LinearLayoutManager(requireContext())
        loadProducts()
    }

    override fun onResume() {
        super.onResume()
        if (_binding != null) loadProducts()
    }

    private fun loadProducts() {
        lifecycleScope.launch {
            val products = AppDatabase.getInstance(requireContext())
                .productDao()
                .getAll()

            binding.recyclerProducts.adapter = ProductAdapter(products) { product ->
                startActivity(
                    Intent(requireContext(), ProductDetailsActivity::class.java)
                        .putExtra(ProductDetailsActivity.EXTRA_PRODUCT_ID, product.id)
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
