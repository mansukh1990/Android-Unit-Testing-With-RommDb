package com.example.androidunittest_roomdatabasetestingroomdb.mvvmtest

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidunittest_roomdatabasetestingroomdb.R
import com.example.androidunittest_roomdatabasetestingroomdb.mvvmtest.adapter.ProductAdapter
import com.example.androidunittest_roomdatabasetestingroomdb.mvvmtest.utils.NetworkResult
import com.example.androidunittest_roomdatabasetestingroomdb.mvvmtest.viewmodel.MainViewModel
import com.example.androidunittest_roomdatabasetestingroomdb.mvvmtest.viewmodel.MainViewModelFactory

class ProductActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var productAdapter: ProductAdapter

    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        recyclerView = findViewById(R.id.rvProductList)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        productAdapter = ProductAdapter(emptyList())
        recyclerView.adapter = productAdapter

        val repository = (application as StoreApplication).productRepository
        viewModel =
            ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        viewModel.products.observe(this) {
            when (it) {
                is NetworkResult.Loading -> {
                    Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                }

                is NetworkResult.Success -> {
                    Log.e("MANSUKH", it.data.toString())
                    productAdapter.updateData(it.data!!)
                }

                is NetworkResult.Error -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewModel.getProducts()

    }
}