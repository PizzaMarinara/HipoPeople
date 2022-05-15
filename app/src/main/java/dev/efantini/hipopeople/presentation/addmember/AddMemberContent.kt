package dev.efantini.hipopeople.presentation.addmember

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.efantini.hipopeople.domain.model.Hipo
import dev.efantini.hipopeople.domain.model.Member
import dev.efantini.hipopeople.presentation.shared.elements.HipoBigButton
import dev.efantini.hipopeople.presentation.shared.elements.HipoForm
import dev.efantini.hipopeople.presentation.shared.elements.HipoFormField
import dev.efantini.hipopeople.presentation.shared.elements.HipoFormState
import dev.efantini.hipopeople.presentation.shared.elements.HipoTopBar
import dev.efantini.hipopeople.presentation.shared.elements.SimpleAlertDialog
import dev.efantini.hipopeople.presentation.shared.theme.BackgroundGrey2
import dev.efantini.hipopeople.presentation.shared.theme.Palette2

@Composable
fun AddMemberContent(
    navController: NavController,
    viewModel: AddMemberViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState().value

    val formState by remember { mutableStateOf(HipoFormState()) }

    val formValidationDialogShowing = rememberSaveable { mutableStateOf(false) }

    val onSaveClicked: () -> Unit = {
        val member = createMemberFromFormState(formState)
        if (member != null) {
            viewModel.addMember(member)
        } else {
            formValidationDialogShowing.value = true
        }
    }

    if (formValidationDialogShowing.value) {
        SimpleAlertDialog(
            onConfirm = {
                formValidationDialogShowing.value = false
            },
            onCancel = {},
            onDismiss = {
                formValidationDialogShowing.value = false
            },
            cancelButtonVisible = false,
            title = { Text(text = "Warning") },
            text = { Text(text = "Some of the fields are not filled. Please fill all the fields.") }
        )
    }

    Scaffold(topBar = {
        HipoTopBar(navController, "Add Member")
    }) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            Column(modifier = Modifier.fillMaxSize()) {
                if (state.error.isNotBlank()) {
                    Text(text = "The following error has occurred: ${state.error}")
                }
                if (state.loading) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .alpha(0.5F)
                            .background(BackgroundGrey2)
                            .zIndex(2F)
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth(0.5F)
                                .fillMaxHeight(),
                            color = Palette2,
                        )
                    }
                }

                Column {
                    HipoForm(
                        state = formState,
                        fields = listOf(
                            HipoFormField(
                                name = "name",
                                label = "Name",
                                hint = "Please enter your name"
                            ),
                            HipoFormField(
                                name = "position",
                                label = "Position",
                                hint = "Please enter your position"
                            ),
                            HipoFormField(
                                name = "age",
                                label = "Age",
                                hint = "Please enter your age",
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                )
                            ),
                            HipoFormField(
                                name = "location",
                                label = "Location",
                                hint = "Please enter your location"
                            ),
                            HipoFormField(
                                name = "yearsInHipo",
                                label = "Number of years in Hipo",
                                hint = "Please enter how many years you worked at Hipo",
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                )
                            ),
                            HipoFormField(
                                name = "github",
                                label = "Github",
                                hint = "Please enter your Github username"
                            )
                        )
                    )
                }
                Box(
                    contentAlignment = Alignment.BottomCenter,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    HipoBigButton(
                        text = "Add Member",
                        onClick = onSaveClicked
                    )
                }
            }
        }
    }
}

private fun createMemberFromFormState(state: HipoFormState): Member? {
    return try {
        Member(
            name = state.getData().getOrDefault("name", ""),
            age = state.getData().getOrDefault("age", "").toInt(),
            location = state.getData().getOrDefault("location", ""),
            github = state.getData().getOrDefault("github", ""),
            hipo = Hipo(
                position = state.getData().getOrDefault("position", ""),
                yearsInHipo = state.getData().getOrDefault("yearsInHipo", "").toInt(),
            )
        )
    } catch (e: Exception) {
        null
    }
}
