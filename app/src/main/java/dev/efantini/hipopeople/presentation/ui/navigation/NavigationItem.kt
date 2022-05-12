package dev.efantini.hipopeople.presentation.ui.navigation

sealed class NavigationItem(
    val route: String,
    val fullRoute: String
) {
    object UsersList : NavigationItem(
        "userslist", "userslist"
    )

    object AddUser : NavigationItem(
        "adduser", "adduser"
    )

    object UserDetail : NavigationItem(
        "userdetail", "userdetail/{userId}"
    )
}
