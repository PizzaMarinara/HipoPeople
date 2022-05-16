package dev.efantini.hipopeople.presentation.shared.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.efantini.hipopeople.presentation.shared.theme.BasicShapes

@Composable
fun HipoTopBar(
    navController: NavController,
    title: String = ""
) {
    val appBarHorizontalPadding = 4.dp
    val titleIconModifier = Modifier.fillMaxHeight()
        .width(72.dp - appBarHorizontalPadding)

    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(Modifier.height(32.dp)) {

            if (navController.previousBackStackEntry != null) {
                Row(titleIconModifier, verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = { navController.navigateUp() },
                        enabled = true,
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                }
            }

            Row(
                Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            // Actions
            /*
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Row(
                    Modifier.fillMaxHeight(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    content = actions
                )
            }
            */
        }
    }
}

@Composable
fun HipoBigButton(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = BasicShapes.large
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
