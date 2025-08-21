package com.example.androidunittest_roomdatabasetestingroomdb.mock

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UserServiceTest {

    @Mock
    private lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        Mockito.`when`(userRepository.loginUser(anyString(), anyString()))
            .thenReturn(LOGIN_STATUS.SUCCESS)

    }

    @Test
    fun loginUser() {

        val sut = UserService(userRepository)
        val result = sut.loginUser("abc@gmail.com", "123456")
        Assert.assertEquals("Login Successful", result)
    }

}