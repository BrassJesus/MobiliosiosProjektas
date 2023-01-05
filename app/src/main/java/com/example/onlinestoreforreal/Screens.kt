package com.example.onlinestoreforreal

sealed class Screens(val route: String) {
    object Login: Screens(route =  "Login_Screen")
    object Signup: Screens(route = "Signup_Screen")
    object Products: Screens(route = "Product_Screen")
}
