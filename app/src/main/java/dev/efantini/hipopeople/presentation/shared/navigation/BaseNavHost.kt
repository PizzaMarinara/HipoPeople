package dev.efantini.hipopeople.presentation.shared.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.efantini.hipopeople.presentation.addmember.AddMemberContent
import dev.efantini.hipopeople.presentation.memberdetail.MemberDetailContent
import dev.efantini.hipopeople.presentation.memberslist.MembersListContent

@ExperimentalMaterialApi
@Composable
fun BaseNavHost(
    startDestination: String = NavigationItem.MembersList.route
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(NavigationItem.MembersList.fullRoute) {
            MembersListContent(navController)
        }
        composable(NavigationItem.AddMember.fullRoute) {
            AddMemberContent(navController)
        }
        composable(NavigationItem.MemberDetail.fullRoute) {
            MemberDetailContent(navController)
        }
    }
}
