package dev.efantini.hipopeople.presentation.shared

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import dev.efantini.hipopeople.presentation.shared.navigation.BaseNavHost
import dev.efantini.hipopeople.presentation.shared.theme.HipoPeopleTheme

@ExperimentalMaterialApi
@Composable
fun MainContent() {
    HipoPeopleTheme {
        BaseNavHost()
    }
}
