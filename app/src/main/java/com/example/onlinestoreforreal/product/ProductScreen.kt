package com.example.onlinestoreforreal.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.onlinestoreforreal.Screens
import com.example.onlinestoreforreal.auth.AuthViewModel


@Composable
fun ProductScreen(navController: NavController, viewModel: AuthViewModel?) {
    Column(modifier = Modifier
        .background(color = Color.LightGray)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Text(
            text = "Home",
            fontWeight = FontWeight.Bold,

        )
        Button(onClick = { viewModel?.logout() }) {
            navController.navigate(Screens.Login.route){
                popUpTo(Screens.Login.route) {inclusive = true}
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProductScreenPreview(){
    ProductScreen(navController = rememberNavController(), null)
}