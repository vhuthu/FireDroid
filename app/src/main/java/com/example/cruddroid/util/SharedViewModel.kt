package com.example.cruddroid.util

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class SharedViewModel(

):ViewModel() {

    fun saveData(userData: UserData, context: Context ) = CoroutineScope(Dispatchers.IO). launch{

        val fireStoreRef = Firebase.firestore
            .collection("user")
            .document(userData.userID)

        try {
            fireStoreRef.set(userData).addOnSuccessListener {
                Toast.makeText(context,"Successfully saved data", Toast.LENGTH_LONG).show()

            }

        }catch (e: Exception){
            Toast.makeText(context,e.message, Toast.LENGTH_LONG).show()
        }

    }



    fun retrieveData(userID:String,
                     context: Context,
                     data: (UserData) -> Unit

    ) = CoroutineScope(Dispatchers.IO). launch{

        val fireStoreRef = Firebase.firestore
            .collection("user")
            .document(userID)

        try {
            fireStoreRef.get().addOnSuccessListener {
               if (it.exists()){
                   val userData = it.toObject<UserData>()!!
                   data(userData)
               }else{
                   Toast.makeText(context,"No Data Found" ,Toast.LENGTH_SHORT).show()
               }

            }

        }catch (e: Exception){
            Toast.makeText(context,e.message, Toast.LENGTH_LONG).show()
        }

    }


    fun deleteData(
        userID:String,
        context: Context,
        navController: NavController

    ) = CoroutineScope(Dispatchers.IO). launch{

        val fireStoreRef = Firebase.firestore
            .collection("user")
            .document(userID)

        try {
            fireStoreRef.delete().addOnSuccessListener {
                Toast.makeText(context,"Successfully saved data", Toast.LENGTH_LONG).show()
                navController.popBackStack()
            }

        }catch (e: Exception){
            Toast.makeText(context,e.message, Toast.LENGTH_LONG).show()
        }

    }
}