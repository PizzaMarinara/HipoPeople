package dev.efantini.hipopeople.domain.use_case

import dev.efantini.hipopeople.domain.model.Member
import dev.efantini.hipopeople.domain.repository.MemberRepository
import dev.efantini.hipopeople.shared.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMembersUseCase(
    private val repository: MemberRepository
) {

    fun execute(query: String = ""): Flow<Resource<List<Member>>> {
        return repository.getElements().map { resource ->
            if (resource is Resource.Success) {
                Resource.Success(
                    resource.data.filter { it.name.contains(query) }
                )
            } else {
                resource
            }
        }
    }
}
