package com.example.cruddroid.nav
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cruddroid.screen.AddDataScreen
import com.example.cruddroid.screen.GetDataScreen
import com.example.cruddroid.screen.MainScreen
import com.example.cruddroid.util.SharedViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {

    NavHost(navController = navController, startDestination = Screens.MainScreen.route ){

        //main_screen
        composable(route = Screens.MainScreen.route){
           MainScreen(navController = navController)
        }

        //get_data
        composable(route = Screens.GetDataScreen.route){
            GetDataScreen(navController = navController, sharedViewModel = sharedViewModel)
        }

        //add_data
        composable(route = Screens.AddDataScreen.route){
            AddDataScreen(navController = navController, sharedViewModel = sharedViewModel)
        }
    }
}