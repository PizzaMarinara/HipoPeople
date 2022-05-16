package dev.efantini.hipopeople.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserGithubPlanDto(
    val name: String?,
    val space: Int?,
    @Json(name = "private_repos")
    val privateRepos: Int?,
    val collaborators: Int?
)
