package com.example.onlinestoreforreal.auth


import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.onlinestoreforreal.Screens
import com.example.onlinestoreforreal.data.Resource
import com.example.onlinestoreforreal.auth.AuthViewModel

//@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterScreen(navController: NavController, viewModel: AuthViewModel?) {

    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
/*
    val isEmailValid by derivedStateOf {
        Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    val isPasswordValid by derivedStateOf {
        password.length > 7
    }
*/
    val focusManager = LocalFocusManager.current

    val signUpFlow = viewModel?.signupFlow?.collectAsState()

    //val refLoader = createRefs()


    Column(modifier = Modifier
        .background(color = Color.LightGray)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Sign up",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .padding(all = 12.dp)
                .background(color = Color.White)


        ){
            OutlinedTextField(value = email, onValueChange = { email = it },
                label = {
                    Text(text = "Email:")
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {focusManager.moveFocus(FocusDirection.Down)}
                )
            )
            OutlinedTextField(value = password, onValueChange = { password = it },
                label = {
                    Text(text = "Password:")
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onNext = {focusManager.clearFocus()}
                )
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = { viewModel?.signupUser(email, password) },
                    enabled = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                ) {
                    Text(
                        text = "Register",
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
    signUpFlow?.value?.let {
        when(it) {
            is Resource.Failure -> {
                val context = LocalContext.current
                Toast.makeText(context, it.exception.message, Toast.LENGTH_LONG).show()
            }
            Resource.Loading -> {

                CircularProgressIndicator()
            }
            is Resource.Success -> {
                navController.navigate(Screens.Products.route){
                    popUpTo(Screens.Signup.route) { inclusive = true }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun RegisterScreenPreview(){
   RegisterScreen(navController = rememberNavController(), null)
}