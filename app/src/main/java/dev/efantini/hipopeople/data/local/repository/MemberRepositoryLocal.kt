package dev.efantini.hipopeople.data.local.repository

import dev.efantini.hipopeople.data.local.dao.MemberDao
import dev.efantini.hipopeople.domain.model.Member
import dev.efantini.hipopeople.domain.repository.MemberRepository
import kotlinx.coroutines.flow.Flow

class MemberRepositoryLocal(
    private val dao: MemberDao
) : MemberRepository {
    override fun getElements(): Flow<List<Member>> {
        return dao.getElements()
    }

    override suspend fun putElement(element: Member) {
        return dao.putElement(element)
    }
}
