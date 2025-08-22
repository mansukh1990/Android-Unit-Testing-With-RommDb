package com.example.androidunittest_roomdatabasetestingroomdb.mvvmunittest

import android.app.Application
import com.example.androidunittest_roomdatabasetestingroomdb.mvvmunittest.api.ProductApi
import com.example.androidunittest_roomdatabasetestingroomdb.mvvmunittest.repository.ProductRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StoreApplication : Application() {

    lateinit var productApi: ProductApi
    lateinit var productRepository: ProductRepository

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        productApi = retrofit.create(ProductApi::class.java)
        productRepository = ProductRepository(productApi)
    }
}