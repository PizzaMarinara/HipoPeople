package dev.efantini.hipopeople.presentation.memberdetail.elements

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import dev.efantini.hipopeople.domain.model.GithubRepositoryDetails

@ExperimentalMaterialApi
@Composable
fun RepoListItem(
    repo: GithubRepositoryDetails
) {
    Card {
        Row {
            Text(repo.name)
        }
    }
}
