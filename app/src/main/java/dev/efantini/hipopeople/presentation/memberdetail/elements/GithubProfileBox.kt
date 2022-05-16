package dev.efantini.hipopeople.presentation.memberdetail.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import dev.efantini.hipopeople.R
import dev.efantini.hipopeople.domain.model.GithubProfileDetails

@ExperimentalMaterialApi
@Composable
fun GithubProfileBox(
    profileDetails: GithubProfileDetails
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(profileDetails.avatarUrl)
                .transformations(CircleCropTransformation())
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.userimage),
            contentDescription = "Profile",
            contentScale = ContentScale.Fit
        )
        Row(
            modifier = Modifier.wrapContentWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Followers")
                Text(profileDetails.followers.toString())
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Following")
                Text(profileDetails.following.toString())
            }
        }
    }
}
