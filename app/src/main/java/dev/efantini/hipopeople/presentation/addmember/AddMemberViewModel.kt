package dev.efantini.hipopeople.presentation.addmember

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.efantini.hipopeople.domain.model.Hipo
import dev.efantini.hipopeople.domain.model.Member
import dev.efantini.hipopeople.domain.use_case.AddMemberUseCase
import dev.efantini.hipopeople.presentation.addmember.elements.HipoFormState
import dev.efantini.hipopeople.presentation.addmember.states.AddMemberState
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class AddMemberViewModel @Inject constructor(
    private val addMemberUseCase: AddMemberUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddMemberState(loading = false))
    val uiState: StateFlow<AddMemberState> = _uiState.asStateFlow()

    fun addMember(formState: HipoFormState) {

        _uiState.update {
            it.copy(
                loading = true
            )
        }
        val member = createMemberFromFormState(formState)
        if (member == null) {
            _uiState.update {
                it.copy(
                    loading = false,
                    formValidationDialogShowing = true
                )
            }
        } else {
            viewModelScope.launch {

                addMemberUseCase.execute(member)

                _uiState.update {
                    it.copy(
                        loading = false,
                        success = true
                    )
                }
            }
        }
    }

    fun dismissFormValidationDialog() {
        _uiState.update {
            it.copy(
                formValidationDialogShowing = false
            )
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
}
