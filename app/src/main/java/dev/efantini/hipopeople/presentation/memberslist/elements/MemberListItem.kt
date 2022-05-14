package dev.efantini.hipopeople.presentation.memberslist.elements

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import dev.efantini.hipopeople.domain.model.Member

@ExperimentalMaterialApi
@Composable
fun MemberListItem(
    member: Member,
    onClick: (Member) -> Unit
) {
    val cardClicked = {
        onClick(member)
    }
    Card(onClick = cardClicked) {
        Row {
            Text(member.name)
        }
    }
}
