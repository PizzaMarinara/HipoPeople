package dev.efantini.hipopeople.domain.use_case

import dev.efantini.hipopeople.domain.model.Member
import dev.efantini.hipopeople.domain.repository.MemberRepository
import dev.efantini.hipopeople.shared.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMembersUseCase(
    private val repository: MemberRepository
) {

    /**
     * If a query parameter isn't set, only elements are returned. Otherwise, the elements are
     * filtered, considering the fields that are listed in the filter() function.
     *
     * This could be simplified to always run the filter no matter the query, but it's assumed
     * that the filter function could get expensive in the future, so we don't run it unnecessarily.
     */
    fun execute(query: String = ""): Flow<Resource<List<Member>>> {
        return if (query.isBlank()) {
            repository.getElements()
        } else {
            repository.getElements().map { resource ->
                if (resource is Resource.Success) {
                    Resource.Success(
                        resource.data.filter {
                            filter(it, query)
                        }
                    )
                } else {
                    resource
                }
            }
        }
    }

    private fun filter(member: Member, query: String): Boolean {
        return when {
            member.name.contains(query) -> true
            member.github.contains(query) -> true
            member.hipo.position.contains(query) -> true
            else -> false
        }
    }
}
