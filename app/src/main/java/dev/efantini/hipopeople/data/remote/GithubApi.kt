package dev.efantini.hipopeople.data.remote

import dev.efantini.hipopeople.data.remote.dto.UserGithubDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): Call<UserGithubDto>
}
