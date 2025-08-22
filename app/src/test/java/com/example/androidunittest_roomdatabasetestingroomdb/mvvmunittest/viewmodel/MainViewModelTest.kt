package com.example.androidunittest_roomdatabasetestingroomdb.mvvmunittest.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.androidunittest_roomdatabasetestingroomdb.getOrAwaitValue
import com.example.androidunittest_roomdatabasetestingroomdb.mvvmunittest.repository.ProductRepository
import com.example.androidunittest_roomdatabasetestingroomdb.mvvmunittest.utils.NetworkResult
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import kotlin.test.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    private val testDispatcherStandard = StandardTestDispatcher()
  //  private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var sut: MainViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Mock
    lateinit var repository: ProductRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcherStandard)
        sut = MainViewModel(repository)
    }

    @Test
    fun test_GetProducts() = runTest {
        // Arrange
        Mockito.`when`(repository.getProducts())
            .thenReturn(NetworkResult.Success(emptyList()))

        // Act
        sut.getProducts()
        testDispatcherStandard.scheduler.advanceUntilIdle()

        // Assert
        val result = sut.products.getOrAwaitValue()
        assertEquals(0, result.data!!.size)
    }

    @Test
    fun test_GetProducts_expectedError() = runTest {
        // Arrange
        Mockito.`when`(repository.getProducts())
            .thenReturn(NetworkResult.Error("Something went wrong"))

        // Act
        sut.getProducts()
        testDispatcherStandard.scheduler.advanceUntilIdle()

        // Assert
        val result = sut.products.getOrAwaitValue()
        assertTrue(result is NetworkResult.Error)
        // Assert.assertEquals(true, result as NetworkResult.Error)
        assertEquals("Something went wrong", result.message)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}