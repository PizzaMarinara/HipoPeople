package dev.efantini.hipopeople.presentation.memberdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.efantini.hipopeople.domain.use_case.GetGithubUserUseCase
import dev.efantini.hipopeople.presentation.memberdetail.states.MemberDetailState
import dev.efantini.hipopeople.shared.Resource
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

@HiltViewModel
class MemberDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getGithubUserUseCase: GetGithubUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MemberDetailState(loading = true))
    val uiState: StateFlow<MemberDetailState> = _uiState.asStateFlow()

    init {
        savedStateHandle.get<String>("memberId")?.let { getUser(it) }
    }

    private fun getUser(username: String) {
        getGithubUserUseCase.execute(username).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            loading = false,
                            githubProfile = result.data
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
