package com.example.androidunittest_roomdatabasetestingroomdb.mvvmunittest.models

data class ProductListItem(
    val image: String,
    val price: Double,
    val rating: Rating,
    val description: String,
    val id: Long,
    val title: String,
    val category: String
)

data class Rating(
    val rate: Double,
    val count: Long
)
