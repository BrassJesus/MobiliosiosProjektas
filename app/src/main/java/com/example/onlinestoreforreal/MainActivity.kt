package com.example.onlinestoreforreal


import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState

import androidx.compose.runtime.*

import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.onlinestoreforreal.auth.LoginScreen
import com.example.onlinestoreforreal.auth.AuthViewModel
import com.example.onlinestoreforreal.auth.RegisterScreen
import com.example.onlinestoreforreal.product.ProductScreen


import com.example.onlinestoreforreal.ui.theme.OnlineStoreForRealTheme
import dagger.hilt.android.AndroidEntryPoint
import java.lang.reflect.Modifier


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val AuthenticationviewModel by viewModels<AuthViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val snackbarHostState = remember {SnackbarHostState() }
            Scaffold(
                snackbarHost = { SnackbarHost(snackbarHostState) },
                content = {
                    OnlineStoreForRealTheme {
                        setupNavGraph(AuthenticationviewModel)
                    }
                }
            )


            }
        }
    }

@Composable
fun setupNavGraph(
    viewModel : AuthViewModel,
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController,
    startDestination = Screens.Login.route
        ){
        composable(route = Screens.Login.route){
            LoginScreen(navController = navController, viewModel)
        }
        composable(route = Screens.Signup.route){
            RegisterScreen(navController = navController, viewModel)
        }
        composable(route = Screens.Products.route){
            ProductScreen(navController = navController, viewModel)
        }
    }
}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    OnlineStoreForRealTheme {
        //()
    }
}

