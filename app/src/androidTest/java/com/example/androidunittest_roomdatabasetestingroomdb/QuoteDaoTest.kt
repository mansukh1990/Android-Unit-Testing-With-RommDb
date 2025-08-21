package com.example.androidunittest_roomdatabasetestingroomdb

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.androidunittest_roomdatabasetestingroomdb.TestWithRoomDb.Quote
import com.example.androidunittest_roomdatabasetestingroomdb.TestWithRoomDb.QuoteDatabase
import com.example.androidunittest_roomdatabasetestingroomdb.TestWithRoomDb.QuotesDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class QuoteDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var quoteDao: QuotesDao
    private lateinit var quoteDatabase: QuoteDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        quoteDatabase = Room.inMemoryDatabaseBuilder(context, QuoteDatabase::class.java).build()
        quoteDao = quoteDatabase.quoteDao()
    }

    @Test
    fun insertQuote_expected_singleQuote() = runBlocking {
        val quote = Quote(0, "This is a test quote", "Author")
        quoteDao.insertQuote(quote)

        val result = quoteDao.getQuotes().getOrAwaitValue()

        Assert.assertEquals(1, result.size)
        Assert.assertEquals("This is a test quote", result[0].text)
    }

    @Test
    fun deleteQuote_expected_noResult() = runBlocking {
        val quote = Quote(0, "This is a test quote", "Author")
        quoteDao.insertQuote(quote)
        quoteDao.delete()

        val result = quoteDao.getQuotes().getOrAwaitValue()

        Assert.assertEquals(0,result.size)

    }

    @After
    fun tearDown() {
        quoteDatabase.close()
    }
}