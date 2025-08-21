package com.example.androidunittest_roomdatabasetestingroomdb.mock

import android.content.Context
import android.content.res.AssetManager
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.doReturn

class QuoteManagerTest {

    @Mock
    lateinit var context: Context

    @Mock
    lateinit var assetManager: AssetManager

    @Before
    fun setUp() {

        MockitoAnnotations.openMocks(this)


    }

    @Test
    fun populateQuoteFromAssets() {
        val testString = QuoteManagerTest::class.java.getResourceAsStream("/quotes.json")
        doReturn(assetManager).`when`(context).assets
        Mockito.`when`(context.assets.open(anyString())).thenReturn(testString)

        val sut = QuoteManager()
        sut.populateQuoteFromAssets(context, "")
        val quote = sut.getCurrentQuote()
        Assert.assertEquals(
            "Genius is one percent inspiration and ninety-nine percent perspiration.",
            quote.text
        )
    }

}