package dev.efantini.hipopeople.presentation.memberslist

import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.efantini.hipopeople.domain.model.Member
import dev.efantini.hipopeople.presentation.memberslist.elements.MemberListItem
import dev.efantini.hipopeople.presentation.memberslist.elements.SearchView
import dev.efantini.hipopeople.presentation.shared.elements.HipoBigButton
import dev.efantini.hipopeople.presentation.shared.elements.HipoTopBar
import dev.efantini.hipopeople.presentation.shared.navigation.NavigationItem
import dev.efantini.hipopeople.presentation.shared.theme.BackgroundGrey2
import dev.efantini.hipopeople.presentation.shared.theme.Palette2
import dev.efantini.hipopeople.shared.filter

@ExperimentalMaterialApi
@Composable
fun MembersListContent(
    navController: NavController,
    viewModel: MembersListViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState().value

    val onMemberClicked: (Member) -> Unit = {
        navController
            .navigate(NavigationItem.MemberDetail.route + "/${it.github}")
    }

    val onAddMemberClicked: () -> Unit = {
        navController.navigate(NavigationItem.AddMember.route)
    }

    Scaffold(topBar = {
        HipoTopBar(navController, "Members")
    }) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                var query by rememberSaveable { mutableStateOf("") }

                if (state.error.isNotBlank()) {
                    Text(text = "The following error has occurred: ${state.error}")
                } else if (state.loading) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .alpha(0.5F)
                            .background(BackgroundGrey2)
                            .zIndex(2F)
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth(0.5F)
                                .fillMaxHeight(),
                            color = Palette2,
                        )
                    }
                } else {

                    LazyColumn(
                        modifier = Modifier.fillMaxWidth()
                            .weight(1f)
                    ) {
                        items(
                            state.members.filter {
                                it.filter(query)
                            }
                        ) {
                            MemberListItem(
                                member = it,
                                onClick = onMemberClicked
                            )
                        }
                    }
                    Box(
                        contentAlignment = Alignment.BottomCenter,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(15.dp)
                        ) {
                            SearchView(
                                modifier = Modifier.fillMaxWidth(0.9F),
                                query = query,
                                onQueryChange = {
                                    query = it
                                }
                            )
                            HipoBigButton(
                                modifier = Modifier.fillMaxWidth(0.9F),
                                text = "Add Member",
                                onClick = onAddMemberClicked
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                        }
                    }
                }
            }
        }
    }
}
