package dev.efantini.hipopeople.presentation.ui.navigation

sealed class NavigationItem(
    val route: String,
    val fullRoute: String
) {
    object MembersList : NavigationItem(
        "memberslist", "memberslist"
    )

    object AddMember : NavigationItem(
        "addmember", "addmember"
    )

    object MemberDetail : NavigationItem(
        "memberdetail", "memberdetail/{memberId}"
    )
}
