package com.example.androidunittest_roomdatabasetestingroomdb.mvvmtest.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidunittest_roomdatabasetestingroomdb.mvvmtest.models.ProductListItem
import com.example.androidunittest_roomdatabasetestingroomdb.mvvmtest.repository.ProductRepository
import com.example.androidunittest_roomdatabasetestingroomdb.mvvmtest.utils.NetworkResult
import kotlinx.coroutines.launch

class MainViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _products = MutableLiveData<NetworkResult<List<ProductListItem>>>()
    val products: LiveData<NetworkResult<List<ProductListItem>>>
        get() = _products

    fun getProducts() {
        viewModelScope.launch {
            _products.postValue(NetworkResult.Loading())
            val response = productRepository.getProducts()
            Log.e("VIEWMODEL",response.data.toString() )
            _products.postValue(response)
        }

    }
}