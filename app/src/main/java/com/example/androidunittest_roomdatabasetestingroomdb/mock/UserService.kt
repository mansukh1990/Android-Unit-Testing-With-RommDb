package com.example.androidunittest_roomdatabasetestingroomdb.mock

class UserService(private val userRepository: UserRepository) {

    fun loginUser(email: String, password: String): String {

        val status = userRepository.loginUser(email, password)
        return when (status) {
            LOGIN_STATUS.INVALID_USER -> "User Not Found"
            LOGIN_STATUS.INVALID_PASSWORD -> "Invalid Password"
            LOGIN_STATUS.UNKNOWN_ERROR -> "Unknown Error"
            LOGIN_STATUS.SUCCESS -> "Login Successful"
        }
    }
}