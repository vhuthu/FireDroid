package com.example.cruddroid.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cruddroid.util.SharedViewModel
import com.example.cruddroid.util.UserData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDataScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
   var userID : String by remember { mutableStateOf("") }
    var name : String by remember { mutableStateOf("") }
    var cellnumber : String by remember { mutableStateOf("") }
    var password : String by remember { mutableStateOf("") }
    var idNum : String by remember { mutableStateOf("") }
    var IdnumInt : Int by remember { mutableStateOf(0) }

//here is the context that is gonna let me route between composables lets go!!!!

    val context = LocalContext.current

    //now lets make the layout to feed the information lets go!!!!

    Column(modifier = Modifier.fillMaxSize()) {
        
        //back button lies here
        Row(
            modifier = Modifier
                .padding(start = 15.dp, top = 15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
            ) {
            IconButton(
                onClick = { navController.popBackStack() }

            ) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back_button")
            }
        }
        //add data layout here

        Column(
            modifier = Modifier
                .padding(start = 60.dp, end = 60.dp, bottom = 50.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            //userId 
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = userID ,
                onValueChange = {userID = it },
                label = { Text(text = "User ID")})

            // name
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = name ,
                onValueChange = {name = it },
                label = { Text(text = "NAME")})

            // cellnumber
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = cellnumber ,
                onValueChange = {cellnumber = it },
                label = { Text(text = "Cell Number")})

            // password
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password ,
                onValueChange = {password = it },
                label = { Text(text = "Password")})

            //id number
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = idNum ,
                onValueChange = {
                    idNum = it
                    if (idNum.isNotEmpty()){
                        IdnumInt = idNum.toInt()
                    }
                                },
                label = { Text(text = "ID NUMBER")},
                keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Number)
            )
            
            //save button here !!
            Button(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth()
                ,onClick = {
                    val userData = UserData(
                        userID = userID,
                        name = name,
                        cellnumber = cellnumber,
                        password = password,
                        idNum = IdnumInt
                    )
                    sharedViewModel.saveData(userData = userData, context = context)
                })
            {
                Text(text = "Save Data")
            }

        }
    }
}


