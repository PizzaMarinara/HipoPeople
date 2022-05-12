package dev.efantini.hipopeople.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun BaseNavHost(
    startDestination: String = NavigationItem.UsersList.route
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(NavigationItem.UsersList.route) {
            // UsersListContent(navController)
        }
        composable(NavigationItem.AddUser.route) {
            // AddUserContent(navController)
        }
        composable(NavigationItem.UserDetail.route) { backStack ->
            // UserDetailContent(navController, backStack.arguments?.getString("userId"))
        }
    }
}
