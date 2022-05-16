package dev.efantini.hipopeople.domain.use_case

import dev.efantini.hipopeople.domain.model.Member
import dev.efantini.hipopeople.domain.repository.MemberRepository
import dev.efantini.hipopeople.shared.Resource
import kotlinx.coroutines.flow.Flow

class GetMembersUseCase(
    private val repository: MemberRepository
) {

    fun execute(): Flow<Resource<List<Member>>> {
        return repository.getElements()
    }
}
