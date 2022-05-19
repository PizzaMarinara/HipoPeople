package dev.efantini.hipopeople.presentation.addmember

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.efantini.hipopeople.R
import dev.efantini.hipopeople.presentation.addmember.elements.HipoForm
import dev.efantini.hipopeople.presentation.addmember.elements.HipoFormField
import dev.efantini.hipopeople.presentation.addmember.elements.HipoFormState
import dev.efantini.hipopeople.presentation.shared.elements.HipoBigButton
import dev.efantini.hipopeople.presentation.shared.elements.HipoTopBar
import dev.efantini.hipopeople.presentation.shared.elements.SimpleAlertDialog
import dev.efantini.hipopeople.presentation.shared.navigation.NavigationItem

@Composable
fun AddMemberContent(
    navController: NavController,
    viewModel: AddMemberViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState().value

    val formState by remember { mutableStateOf(HipoFormState()) }

    val onSaveClicked: () -> Unit = {
        viewModel.addMember(formState)
    }
    LaunchedEffect(Unit) {
        viewModel
            .uiState
            .collect { state ->
                if (state.success) {
                    navController.navigate(NavigationItem.MembersList.route) {
                        popUpTo(NavigationItem.MembersList.route) { inclusive = true }
                    }
                }
            }
    }

    if (state.formValidationDialogShowing) {
        SimpleAlertDialog(
            onConfirm = {
                viewModel.dismissFormValidationDialog()
            },
            onCancel = {},
            onDismiss = {
                viewModel.dismissFormValidationDialog()
            },
            cancelButtonVisible = false,
            title = { Text(text = stringResource(id = R.string.warning)) },
            text = { Text(text = stringResource(id = R.string.some_fields_not_filled)) }
        )
    }

    Scaffold(topBar = {
        HipoTopBar(navController, stringResource(id = R.string.add_member_top_bar))
    }) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            Column(modifier = Modifier.fillMaxSize()) {
                if (state.error.isNotBlank()) {
                    Text(text = stringResource(id = R.string.error_occurred, state.error))
                } else if (state.loading) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .alpha(0.5F)
                            .zIndex(2F)
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth(0.5F)
                                .fillMaxHeight()
                        )
                    }
                } else {

                    HipoForm(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                            .weight(1F),
                        state = formState,
                        fields = listOf(
                            HipoFormField(
                                name = "name",
                                label = stringResource(id = R.string.name_label),
                                hint = stringResource(id = R.string.name_hint)
                            ),
                            HipoFormField(
                                name = "position",
                                label = stringResource(id = R.string.position_label),
                                hint = stringResource(id = R.string.position_hint)
                            ),
                            HipoFormField(
                                name = "age",
                                label = stringResource(id = R.string.age_label),
                                hint = stringResource(id = R.string.age_hint),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                )
                            ),
                            HipoFormField(
                                name = "location",
                                label = stringResource(id = R.string.location_label),
                                hint = stringResource(id = R.string.location_hint)
                            ),
                            HipoFormField(
                                name = "yearsInHipo",
                                label = stringResource(id = R.string.yearsInHipo_label),
                                hint = stringResource(id = R.string.yearsInHipo_hint),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                )
                            ),
                            HipoFormField(
                                name = "github",
                                label = stringResource(id = R.string.github_label),
                                hint = stringResource(id = R.string.github_hint)
                            )
                        )
                    )

                    Box(
                        contentAlignment = Alignment.BottomCenter,
                        modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(15.dp)
                        ) {
                            HipoBigButton(
                                modifier = Modifier.fillMaxWidth(0.9F),
                                text = stringResource(id = R.string.save_button),
                                onClick = onSaveClicked
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                        }
                    }
                }
            }
        }
    }
}
