package dev.efantini.hipopeople.domain.use_case

import dev.efantini.hipopeople.domain.model.GithubProfileDetails
import dev.efantini.hipopeople.domain.repository.GithubUserRepository
import dev.efantini.hipopeople.shared.Resource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetGithubUserUseCase @Inject constructor(
    private val repository: GithubUserRepository
) {

    fun execute(username: String): Flow<Resource<GithubProfileDetails>> {
        return repository.getGithubUser(username)
    }
}
