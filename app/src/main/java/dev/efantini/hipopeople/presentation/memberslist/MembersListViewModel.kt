package dev.efantini.hipopeople.presentation.memberslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.efantini.hipopeople.domain.use_case.GetMembersUseCase
import dev.efantini.hipopeople.presentation.memberslist.states.MembersListState
import dev.efantini.hipopeople.shared.Resource
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

@HiltViewModel
class MembersListViewModel @Inject constructor(
    private val getMembersUseCase: GetMembersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MembersListState(loading = true))
    val uiState: StateFlow<MembersListState> = _uiState.asStateFlow()

    init {
        getUsers()
    }

    private fun getUsers() {
        getMembersUseCase.execute().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            loading = false,
                            members = result.data
                        )
                    }
                }
                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            loading = false,
                            error = result.message
                        )
                    }
                }
                is Resource.Loading -> {
                    _uiState.update {
                        it.copy(
                            loading = true
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}
