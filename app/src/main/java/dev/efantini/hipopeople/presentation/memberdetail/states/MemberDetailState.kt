package dev.efantini.hipopeople.presentation.memberdetail.states

import dev.efantini.hipopeople.domain.model.GithubProfileDetails

data class MemberDetailState(
    val loading: Boolean = false,
    val error: String = "",
    val githubProfile: GithubProfileDetails? = null
)
