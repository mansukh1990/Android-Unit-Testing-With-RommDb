package com.example.androidunittest_roomdatabasetestingroomdb.TestWithRoomDb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Quote(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val text: String?,
    val author: String?,
)