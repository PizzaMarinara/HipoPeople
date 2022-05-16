package dev.efantini.hipopeople.presentation.shared.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.efantini.hipopeople.presentation.shared.theme.BasicShapes
import dev.efantini.hipopeople.presentation.shared.theme.Black
import dev.efantini.hipopeople.presentation.shared.theme.Dark4

@Composable
fun HipoTopBar(
    navController: NavController,
    title: String = ""
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = if (navController.previousBackStackEntry != null) {
            {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        } else { null }
    )
}

@Composable
fun HipoBigButton(
    text: String = "",
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(horizontal = 50.dp, vertical = 15.dp),
        shape = BasicShapes.large,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Dark4,
            contentColor = Black
        )
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
                .align(Alignment.CenterVertically),
            text = text,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun SimpleAlertDialog(
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    onDismiss: () -> Unit,
    title: @Composable (() -> Unit)? = null,
    text: @Composable (() -> Unit)? = null,
    cancelButtonVisible: Boolean = true
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = onConfirm
            ) {
                Text(text = "OK")
            }
        },
        dismissButton = {
            if (cancelButtonVisible) {
                TextButton(
                    onClick = onCancel
                ) {
                    Text(text = "Cancel")
                }
            }
        },
        title = title,
        text = text
    )
}
