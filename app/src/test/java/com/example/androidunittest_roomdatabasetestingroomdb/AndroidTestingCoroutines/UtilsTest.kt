package com.example.androidunittest_roomdatabasetestingroomdb.AndroidTestingCoroutines

import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class UtilsTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Test
    fun getUserName() {

        val sut = Utils(mainCoroutineRule.testDispatcher)
        runTest { sut.getUserName() }
    }

    @Test
    fun getUser() {
        val sut = Utils(mainCoroutineRule.testDispatcher)
        runTest { sut.getUser() }
    }

    @Test
    fun getAddress() {
        val sut = Utils(mainCoroutineRule.testDispatcher)
        runTest { sut.getAddress() }

    }

    @Test
    fun getAddressDetails() {
        val sut = Utils(mainCoroutineRule.testDispatcher)
        runTest {
            sut.getAddressDetails()
            mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
            Assert.assertEquals(true, sut.globalArg)
        }

    }

}