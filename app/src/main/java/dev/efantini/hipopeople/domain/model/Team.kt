package dev.efantini.hipopeople.domain.model

data class Team(
    val company: String,
    val team: String,
    val members: List<Member>
)
