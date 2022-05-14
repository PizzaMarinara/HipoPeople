package dev.efantini.hipopeople.data.local.repository

import dev.efantini.hipopeople.data.local.dao.MemberDao
import dev.efantini.hipopeople.domain.model.Member
import dev.efantini.hipopeople.domain.repository.MemberRepository
import dev.efantini.hipopeople.shared.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MemberRepositoryLocal(
    private val dao: MemberDao
) : MemberRepository {

    override fun getElements(): Flow<Resource<List<Member>>> = flow {
        emit(Resource.Loading(null))
        dao.getElements().catch { e ->
            emit(Resource.Error(e.localizedMessage ?: "Database Error"))
        }.collect {
            emit(Resource.Success(it))
        }
    }

    override suspend fun putElement(element: Member) {
        return dao.putElement(element)
    }
}
