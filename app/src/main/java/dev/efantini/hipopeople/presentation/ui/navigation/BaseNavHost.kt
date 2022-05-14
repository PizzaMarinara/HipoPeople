package dev.efantini.hipopeople.presentation.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.efantini.hipopeople.presentation.memberslist.MembersListContent

@ExperimentalMaterialApi
@Composable
fun BaseNavHost(
    startDestination: String = NavigationItem.MembersList.route
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(NavigationItem.MembersList.route) {
            MembersListContent(navController)
        }
        composable(NavigationItem.AddMember.route) {
            // AddUserContent(navController)
        }
        composable(NavigationItem.MemberDetail.route) { backStack ->
            // UserDetailContent(navController, backStack.arguments?.getString("memberId"))
        }
    }
}
