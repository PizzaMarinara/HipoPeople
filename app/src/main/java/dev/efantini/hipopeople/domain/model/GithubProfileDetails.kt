package dev.efantini.hipopeople.domain.model

data class GithubProfileDetails(
    val avatarUrl: String,
    val login: String,
    val followers: Int,
    val following: Int,
    val repositories: List<GithubRepositoryDetails>
)
