package dev.efantini.hipopeople.domain.repository

import dev.efantini.hipopeople.domain.model.Member
import dev.efantini.hipopeople.shared.Resource
import kotlinx.coroutines.flow.Flow

interface MemberRepository {

    fun getElements(): Flow<Resource<List<Member>>>
    suspend fun putElement(element: Member)

    // suspend fun deleteElement(element: Member)
    // suspend fun getElementById(id: Any): Member?
}
