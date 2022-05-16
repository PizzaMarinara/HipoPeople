package dev.efantini.hipopeople.data.remote.repository

import dev.efantini.hipopeople.data.remote.GithubApi
import dev.efantini.hipopeople.domain.model.GithubProfileDetails
import dev.efantini.hipopeople.domain.repository.GithubUserRepository
import dev.efantini.hipopeople.shared.Resource
import dev.efantini.hipopeople.shared.toGithubProfileDetails
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class GithubUserRepositoryRemote @Inject constructor(
    private val githubApi: GithubApi
) : GithubUserRepository {
    override fun getGithubUser(username: String): Flow<Resource<GithubProfileDetails>> =
        flow {
            emit(Resource.Loading(null))
            try {
                emit(
                    Resource.Success(
                        githubApi.getGithubUser(username)
                            .toGithubProfileDetails(githubApi.getGithubUserRepos(username))
                    )
                )
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "HTTP ${e.code()} Exception."))
            } catch (e: IOException) {
                emit(Resource.Error("Couldn't reach remote API. Check your internet/permissions."))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error("API returned an error."))
            }
        }
}
