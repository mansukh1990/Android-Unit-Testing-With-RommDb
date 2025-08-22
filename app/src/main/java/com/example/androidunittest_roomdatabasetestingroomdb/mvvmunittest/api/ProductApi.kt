package com.example.androidunittest_roomdatabasetestingroomdb.mvvmunittest.api

import com.example.androidunittest_roomdatabasetestingroomdb.mvvmunittest.models.ProductListItem
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {

    @GET("/products")
    suspend fun getProducts(): Response<List<ProductListItem>>
}