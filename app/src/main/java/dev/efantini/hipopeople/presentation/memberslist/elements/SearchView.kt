package dev.efantini.hipopeople.presentation.memberslist.elements

import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SearchView(
    modifier: Modifier = Modifier,
    query: String = "",
    onQueryChange: (String) -> Unit
) {
    TextField(
        modifier = modifier,
        value = query,
        placeholder = { Text("Search") },
        onValueChange = {
            onQueryChange(it)
        },
        trailingIcon = {
            Icon(
                Icons.Default.Clear,
                contentDescription = "Clear",
                modifier = Modifier
                    .clickable {
                        onQueryChange("")
                    }
            )
        }
    )
}
