package com.example.androidunittest_roomdatabasetestingroomdb.AndroidTestingCoroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//avoid hard code dispatchers
class Utils(val dispatcher: CoroutineDispatcher) {

    suspend fun getUserName(): String {
        delay(10000)
        return "Android"
    }

    suspend fun getUser(): String {
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
        }
        return "User - Android Dev"
    }

    suspend fun getAddress(): String {
        withContext(dispatcher) {
            delay(5000)
        }
        return "Address"
    }

    var globalArg = false
    fun getAddressDetails() {
        CoroutineScope(dispatcher).launch {
            globalArg = true
        }
    }
}