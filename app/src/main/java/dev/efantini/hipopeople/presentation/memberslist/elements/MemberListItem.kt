package dev.efantini.hipopeople.presentation.memberslist.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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

    Card(
        onClick = cardClicked,
        elevation = 10.dp,
        modifier = Modifier
            .fillMaxWidth(0.9F)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(member.name)
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = ""
            )
        }
    }
}
