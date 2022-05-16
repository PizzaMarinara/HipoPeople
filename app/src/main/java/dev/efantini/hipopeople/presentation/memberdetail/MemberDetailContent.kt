package dev.efantini.hipopeople.presentation.memberdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.efantini.hipopeople.presentation.memberdetail.elements.GithubProfileBox
import dev.efantini.hipopeople.presentation.memberdetail.elements.RepoListHeader
import dev.efantini.hipopeople.presentation.memberdetail.elements.RepoListItem
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
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                if (state.error.isNotBlank()) {
                    Text(text = "The following error has occurred: ${state.error}")
                } else if (state.loading) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .alpha(0.5F)
                            .zIndex(2F)
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(horizontal = 50.dp, vertical = 80.dp)
                                .fillMaxWidth(0.5F)
                                .fillMaxHeight(0.5F)
                        )
                    }
                } else {
                    state.githubProfile?.let { githubProfile ->
                        Box(
                            contentAlignment = Alignment.BottomCenter,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(15.dp)
                            ) {
                                GithubProfileBox(githubProfile)
                                Spacer(modifier = Modifier.height(5.dp))
                            }
                        }
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth()
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            item {
                                RepoListHeader()
                            }
                            items(githubProfile.repositories) {
                                RepoListItem(repo = it)
                            }
                            item {
                                Spacer(modifier = Modifier.height(5.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}
