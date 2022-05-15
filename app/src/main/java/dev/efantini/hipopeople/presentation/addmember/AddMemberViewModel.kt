package dev.efantini.hipopeople.presentation.addmember

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.efantini.hipopeople.domain.model.Member
import dev.efantini.hipopeople.domain.use_case.AddMemberUseCase
import dev.efantini.hipopeople.presentation.addmember.states.AddMemberState
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class AddMemberViewModel @Inject constructor(
    private val addMemberUseCase: AddMemberUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddMemberState(loading = false))
    val uiState: StateFlow<AddMemberState> = _uiState.asStateFlow()

    fun addMember(member: Member) {
        viewModelScope.launch {

            _uiState.value = AddMemberState(loading = true)
            withContext(Dispatchers.IO) {
                addMemberUseCase.execute(member)
            }
            _uiState.value = AddMemberState(loading = false)
        }
    }
}
