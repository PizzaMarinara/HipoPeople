package dev.efantini.hipopeople.domain.repository

import dev.efantini.hipopeople.domain.model.GithubProfileDetails
import dev.efantini.hipopeople.shared.Resource
import kotlinx.coroutines.flow.Flow

interface GithubUserRepository {
    fun getGithubUser(username: String): Flow<Resource<GithubProfileDetails>>
}
