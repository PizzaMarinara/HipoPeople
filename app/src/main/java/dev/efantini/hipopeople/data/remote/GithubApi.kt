package dev.efantini.hipopeople.data.remote

import dev.efantini.hipopeople.data.remote.dto.UserGithubDto
import dev.efantini.hipopeople.data.remote.dto.UserRepoGithubDto
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("users/{username}")
    suspend fun getGithubUser(@Path("username") username: String): UserGithubDto

    @GET("users/{username}/repos")
    suspend fun getGithubUserRepos(@Path("username") username: String): List<UserRepoGithubDto>
}
