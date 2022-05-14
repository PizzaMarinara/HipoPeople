package dev.efantini.hipopeople.domain.model

data class GithubRepositoryDetails(
    val name: String,
    val updatedAt: String,
    val language: String,
    val stargazersCount: Int
)
