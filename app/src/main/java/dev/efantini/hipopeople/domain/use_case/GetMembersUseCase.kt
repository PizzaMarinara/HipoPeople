package dev.efantini.hipopeople.domain.use_case

import dev.efantini.hipopeople.domain.model.Member
import dev.efantini.hipopeople.domain.repository.MemberRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMembersUseCase(
    private val repository: MemberRepository
) {

    fun execute(query: String = ""): Flow<List<Member>> {
        return repository.getElements().map { members ->
            members.filter {
                it.name.contains(query)
            }
        }
    }
}
