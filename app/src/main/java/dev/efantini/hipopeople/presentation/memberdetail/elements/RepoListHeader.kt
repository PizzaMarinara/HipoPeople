package dev.efantini.hipopeople.presentation.memberdetail.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.efantini.hipopeople.presentation.shared.theme.CuteRed
import dev.efantini.hipopeople.presentation.shared.theme.DarkerRed

@ExperimentalMaterialApi
@Composable
fun RepoListHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (isSystemInDarkTheme()) DarkerRed else CuteRed)
            .height(40.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Repositories",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}
