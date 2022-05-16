package dev.efantini.hipopeople.shared

import dev.efantini.hipopeople.data.remote.dto.UserGithubDto
import dev.efantini.hipopeople.data.remote.dto.UserRepoGithubDto
import dev.efantini.hipopeople.domain.model.GithubProfileDetails
import dev.efantini.hipopeople.domain.model.GithubRepositoryDetails
import dev.efantini.hipopeople.domain.model.Member

fun Member.filter(query: String): Boolean {
    return when {
        this.name.contains(query, ignoreCase = true) -> true
        this.github.contains(query, ignoreCase = true) -> true
        this.hipo.position.contains(query, ignoreCase = true) -> true
        else -> false
    }
}

fun UserGithubDto.toGithubProfileDetails(repos: List<UserRepoGithubDto>): GithubProfileDetails {
    return GithubProfileDetails(
        avatarUrl = avatarUrl,
        login = login,
        followers = followers,
        following = following,
        repositories = repos.map { it.toGithubRepositoryDetails() }
    )
}

fun UserRepoGithubDto.toGithubRepositoryDetails(): GithubRepositoryDetails {
    return GithubRepositoryDetails(
        name = name,
        updatedAt = updatedAt ?: "",
        language = language ?: "",
        stargazersCount = stargazersCount ?: 0
    )
}
