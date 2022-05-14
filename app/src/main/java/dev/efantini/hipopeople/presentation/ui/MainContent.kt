package dev.efantini.hipopeople.presentation.ui

import androidx.compose.runtime.Composable
import dev.efantini.hipopeople.presentation.ui.navigation.BaseNavHost
import dev.efantini.hipopeople.presentation.ui.theme.HipoPeopleTheme

@Composable
fun MainContent() {
    HipoPeopleTheme {
        BaseNavHost()
    }
}
