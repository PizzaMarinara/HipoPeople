package dev.efantini.hipopeople.presentation.memberdetail.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.efantini.hipopeople.domain.model.GithubRepositoryDetails
import dev.efantini.hipopeople.presentation.shared.theme.SuperRed
import dev.efantini.hipopeople.shared.formattedDate

@ExperimentalMaterialApi
@Composable
fun RepoListItem(
    repo: GithubRepositoryDetails
) {
    Card(
        elevation = 10.dp,
        modifier = Modifier
            .fillMaxWidth(0.9F)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1F),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    fontWeight = FontWeight.Bold,
                    text = repo.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    color = SuperRed,
                    text = repo.language
                )
            }
            Spacer(modifier = Modifier.width(2.dp))
            Column(
                modifier = Modifier.weight(1F),
                horizontalAlignment = Alignment.End
            ) {
                Text(repo.updatedAt.formattedDate())
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "",
                        tint = SuperRed
                    )
                    Text(
                        text = repo.stargazersCount.toString()
                    )
                }
            }
        }
    }
}
