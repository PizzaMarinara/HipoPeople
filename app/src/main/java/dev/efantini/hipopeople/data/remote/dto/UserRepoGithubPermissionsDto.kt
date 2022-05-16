package dev.efantini.hipopeople.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserRepoGithubPermissionsDto(
    val admin: Boolean?,
    val push: Boolean?,
    val pull: Boolean?
)
