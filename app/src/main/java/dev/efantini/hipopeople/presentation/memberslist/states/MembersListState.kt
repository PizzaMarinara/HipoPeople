package dev.efantini.hipopeople.presentation.memberslist.states

import dev.efantini.hipopeople.domain.model.Member

data class MembersListState(
    val loading: Boolean = true,
    val error: String = "",
    val members: List<Member> = listOf()
)
