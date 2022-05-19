package dev.efantini.hipopeople.presentation.memberslist.elements

import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dev.efantini.hipopeople.R

@Composable
fun SearchView(
    modifier: Modifier = Modifier,
    query: String = "",
    onQueryChange: (String) -> Unit
) {
    TextField(
        modifier = modifier,
        value = query,
        placeholder = { Text(stringResource(id = R.string.search)) },
        onValueChange = {
            onQueryChange(it)
        },
        trailingIcon = {
            Icon(
                Icons.Default.Clear,
                contentDescription = "",
                modifier = Modifier
                    .clickable {
                        onQueryChange("")
                    }
            )
        },
        colors = TextFieldDefaults.textFieldColors()
    )
}
