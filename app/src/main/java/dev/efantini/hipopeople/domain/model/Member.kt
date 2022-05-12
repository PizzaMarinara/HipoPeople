package dev.efantini.hipopeople.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * We're using the github property as PrimaryKey, it is assumed that a github username is unique.
 */
@Entity
data class Member(
    val name: String,
    val age: Int,
    val location: String,
    @PrimaryKey val github: String,
    @Embedded
    val hipo: Hipo
)
