package com.example.androidunittest_roomdatabasetestingroomdb.mvvmunittest.repository

import android.util.Log
import com.example.androidunittest_roomdatabasetestingroomdb.mvvmunittest.api.ProductApi
import com.example.androidunittest_roomdatabasetestingroomdb.mvvmunittest.models.ProductListItem
import com.example.androidunittest_roomdatabasetestingroomdb.mvvmunittest.utils.NetworkResult

class ProductRepository(
    private val productApi: ProductApi
) {
    suspend fun getProducts(): NetworkResult<List<ProductListItem>> {
        val response = productApi.getProducts()
        Log.e("REPO",response.body().toString() )

        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkResult.Success(responseBody)
            } else {
                NetworkResult.Error("Something went wrong")
            }
        } else {
            NetworkResult.Error("Something went wrong")
        }
    }
}