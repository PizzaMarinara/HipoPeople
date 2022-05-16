package dev.efantini.hipopeople.shared

import dev.efantini.hipopeople.domain.model.Member

fun Member.filter(query: String): Boolean {
    return when {
        this.name.contains(query, ignoreCase = true) -> true
        this.github.contains(query, ignoreCase = true) -> true
        this.hipo.position.contains(query, ignoreCase = true) -> true
        else -> false
    }
}
