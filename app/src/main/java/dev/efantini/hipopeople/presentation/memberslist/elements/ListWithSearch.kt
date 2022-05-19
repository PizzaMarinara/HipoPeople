package dev.efantini.hipopeople.presentation.memberslist.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.efantini.hipopeople.R
import dev.efantini.hipopeople.domain.model.Member
import dev.efantini.hipopeople.presentation.memberslist.states.MembersListState
import dev.efantini.hipopeople.presentation.shared.elements.HipoBigButton
import dev.efantini.hipopeople.shared.filter

@ExperimentalMaterialApi
@Composable
fun ListWithSearch(
    state: MembersListState,
    onMemberClicked: (Member) -> Unit,
    onAddMemberClicked: () -> Unit
) {
    var query by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
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
            item {
                Spacer(modifier = Modifier.height(5.dp))
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
                    text = stringResource(id = R.string.add_member_button),
                    onClick = onAddMemberClicked
                )
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }
}
