package dev.efantini.hipopeople.presentation.memberdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.efantini.hipopeople.presentation.shared.elements.HipoTopBar

@ExperimentalMaterialApi
@Composable
fun MemberDetailContent(
    navController: NavController,
    viewModel: MemberDetailViewModel = hiltViewModel()
) {

    val state = viewModel.uiState.collectAsState().value

    Scaffold(topBar = {
        HipoTopBar(
            navController,
            if (state.githubProfile != null)
                state.githubProfile.login
            else
                "Member Detail"
        )
    }) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            Column {
                Text(text = state.githubProfile?.login ?: "")
                Text(text = state.githubProfile?.followers.toString())
                Text(text = state.githubProfile?.followers.toString())
            }
        }
    }
}
