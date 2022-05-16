package dev.efantini.hipopeople.presentation.addmember.states

import dev.efantini.hipopeople.presentation.addmember.elements.HipoFormState

data class AddMemberState(
    val loading: Boolean = false,
    val error: String = "",
    val formValidationDialogShowing: Boolean = false,
    val formState: HipoFormState = HipoFormState(),
    val success: Boolean = false
)
