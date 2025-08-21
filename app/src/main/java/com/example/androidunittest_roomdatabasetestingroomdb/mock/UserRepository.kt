package com.example.androidunittest_roomdatabasetestingroomdb.mock

class UserRepository {

    val users = listOf<User>(
        User(id = 1, name = "Mansukh", email = "mrm.ec08os@gmail.com", password = "mm1234"),
        User(id = 2, name = "Punit", email = "ps.ec08os@gmail.com", password = "ps1234"),
        User(id = 1, name = "Hemali", email = "hd.ec08os@gmail.com", password = "hd1234"),
    )

    fun loginUser(email: String, password: String): LOGIN_STATUS {

        //in real scenario fetch data from Db
        val users = users.filter { user -> user.email == email }
        return if (users.size == 1) {
            if (users[0].password == password) {
                LOGIN_STATUS.SUCCESS
            } else {
                LOGIN_STATUS.INVALID_PASSWORD
            }
        } else {
            LOGIN_STATUS.INVALID_USER
        }

    }
}