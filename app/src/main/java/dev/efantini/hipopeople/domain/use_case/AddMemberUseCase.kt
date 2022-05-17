package dev.efantini.hipopeople.domain.use_case

import dev.efantini.hipopeople.domain.model.Member
import dev.efantini.hipopeople.domain.repository.MemberRepository
import javax.inject.Inject

class AddMemberUseCase @Inject constructor(
    private val repository: MemberRepository
) {

    suspend fun execute(member: Member) {
        repository.putElement(member)
    }
}
