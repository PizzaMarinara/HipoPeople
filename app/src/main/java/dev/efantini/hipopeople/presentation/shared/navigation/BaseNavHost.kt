package dev.efantini.hipopeople.presentation.shared.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.efantini.hipopeople.presentation.addmember.AddMemberContent
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
            AddMemberContent(navController)
        }
        composable(NavigationItem.MemberDetail.route) { backStack ->
            // MemberDetailContent(navController, backStack.arguments?.getString("memberId"))
        }
    }
}
